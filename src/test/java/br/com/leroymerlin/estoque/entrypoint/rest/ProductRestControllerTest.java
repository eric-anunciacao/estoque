package br.com.leroymerlin.estoque.entrypoint.rest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

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
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.leroymerlin.estoque.usecase.request.PartialUpdateProductRequest;
import br.com.leroymerlin.estoque.usecase.request.UpdateProductRequest;
import br.com.leroymerlin.estoque.usecase.response.ProductResponse;

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
		var headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		var body = new LinkedMultiValueMap<String, Object>();
		var contentStream = this.getClass().getClassLoader().getResourceAsStream("products.csv");
		var file = new MockMultipartFile("file", "file", "text/csv", contentStream);
		body.add("file", file.getResource());

		var entity = new HttpEntity<MultiValueMap<String, Object>>(body, headers);
		var response = restTemplate.postForEntity(getFileBaseUrl(), entity, String.class);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
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
