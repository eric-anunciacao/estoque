package com.easys.estoque.entrypoint.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.IOException;
import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.TestInstance.Lifecycle;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import com.easys.estoque.usecase.request.PartialUpdateProductRequest;
import com.easys.estoque.usecase.request.UpdateProductRequest;
import com.easys.estoque.usecase.response.FindFileStatusResponse;
import com.easys.estoque.usecase.response.ImportProductResponse;
import com.easys.estoque.usecase.response.ProductResponse;

@ActiveProfiles("test")
@TestInstance(Lifecycle.PER_CLASS)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductRestControllerTest {

	private static final String HOST = "localhost";
	private static final String ID = "/1001";

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@BeforeAll
	public void setup() {
		restTemplate.getRestTemplate().setRequestFactory(new HttpComponentsClientHttpRequestFactory());
	}

	@Test
	@Order(1)
	void shouldGetNoContentWhenCallAllProducts() throws InterruptedException {
		var entity = new HttpEntity<String>(null, new HttpHeaders());

		var response = restTemplate.exchange(getProductBaseUrl(), HttpMethod.GET, entity, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	@Order(2)
	void whenFileUploadedThenVerifyStatus() throws Exception {
		var response = shouldUpload("products.csv");

		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());
	}

	@Test
	@Order(3)
	void shouldGetAllProducts() throws InterruptedException {
		Thread.sleep(2000);
		var entity = new HttpEntity<String>(null, new HttpHeaders());

		var response = restTemplate.exchange(getProductBaseUrl(), HttpMethod.GET, entity, String.class);

		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	@Order(4)
	void shouldGetProductById() {
		var product = restTemplate.getForObject(getProductBaseUrl().concat(ID), ProductResponse.class);

		assertNotNull(product);
		assertEquals("Furadeira X", product.getName());
	}

	@Test
	@Order(5)
	void shouldUpdateProductPut() {
		var request = new UpdateProductRequest("Updating name", true, "Updating description", new BigDecimal("1.00"),
				"00001");

		restTemplate.put(getProductBaseUrl().concat(ID), request);

		var product = restTemplate.getForObject(getProductBaseUrl().concat(ID), ProductResponse.class);

		assertNotNull(product);
		assertEquals(request.getName(), product.getName());
		assertEquals(request.isFreeShipping(), product.isFreeShipping());
		assertEquals(request.getDescription(), product.getDescription());
		assertEquals(request.getPrice(), product.getPrice());
		assertEquals(request.getCategory(), product.getCategory());
	}

	@Test
	@Order(6)
	void shouldUpdateProductPatch() {
		var price = new BigDecimal("777.77");
		var request = new PartialUpdateProductRequest(null, null, null, price, null);

		final var requestEntity = new HttpEntity<>(request);
		var response = restTemplate.exchange(getProductBaseUrl().concat(ID), HttpMethod.PATCH, requestEntity,
				String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());

		var product = restTemplate.getForObject(getProductBaseUrl().concat(ID), ProductResponse.class);

		assertNotNull(product);
		assertNotNull(product.getName());
		assertEquals(price, product.getPrice());
	}

	@Test
	@Order(7)
	void shouldDeleteProduct() {
		restTemplate.delete(getProductBaseUrl().concat(ID));

		var entity = new HttpEntity<String>(null, new HttpHeaders());
		var response = restTemplate.exchange(getProductBaseUrl().concat(ID), HttpMethod.GET, entity, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
	}

	@Test
	@Order(8)
	void shouldThrowAnErrorWhenDeleteProduct() {
		var entity = new HttpEntity<String>(null, new HttpHeaders());
		var response = restTemplate.exchange(getProductBaseUrl().concat("/9999"), HttpMethod.DELETE, entity,
				String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	@Order(9)
	void shouldThrowAnErrorWhenUpdateProductPatch() {
		var request = new PartialUpdateProductRequest(null, null, null, new BigDecimal("777.77"), null);

		final var requestEntity = new HttpEntity<>(request);
		var response = restTemplate.exchange(getProductBaseUrl().concat("/9999"), HttpMethod.PATCH, requestEntity,
				String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
	}

	@Test
	@Order(10)
	void shouldReturnFileStatus() throws InterruptedException {
		validateFileStatus(1L, "PROCESSING");

		Thread.sleep(10000);

		validateFileStatus(1L, "PROCESSED");
	}

	@Test
	@Order(11)
	void shouldReturnFileWithErrorStatus() throws Exception {
		var response = shouldUpload("products_with_error.csv");

		assertNotNull(response);
		assertEquals(HttpStatus.CREATED, response.getStatusCode());

		validateFileStatus(response.getBody().getFileId(), "PROCESSING");

		Thread.sleep(3000);

		validateFileStatus(response.getBody().getFileId(), "PROCESSED_WITH_ERROR");
	}

	private ResponseEntity<ImportProductResponse> shouldUpload(String filename) throws IOException {
		var headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		var body = new LinkedMultiValueMap<String, Object>();
		var contentStream = this.getClass().getClassLoader().getResourceAsStream(filename);
		var file = new MockMultipartFile("file", "file", "text/csv", contentStream);
		body.add("file", file.getResource());

		var entity = new HttpEntity<MultiValueMap<String, Object>>(body, headers);
		return restTemplate.postForEntity(getFileBaseUrl(), entity, ImportProductResponse.class);
	}

	private void validateFileStatus(Long id, String expectedStatus) {
		var response = findFileStatus(id);

		var body = response.getBody();
		assertNotNull(body);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(expectedStatus, body.getStatus());
	}

	private ResponseEntity<FindFileStatusResponse> findFileStatus(Long id) {
		var entity = new HttpEntity<String>(null, new HttpHeaders());
		var uri = String.format("/%d/status", id);
		var url = getFileBaseUrl().concat(uri);
		return restTemplate.exchange(url, HttpMethod.GET, entity, FindFileStatusResponse.class);
	}

	private String getProductBaseUrl() {
		return getBaseUrl("products");
	}

	private String getFileBaseUrl() {
		return getBaseUrl("files");
	}

	private String getBaseUrl(String source) {
		return String.format("http://%s:%s/%s", HOST, port, source);
	}

}
