package br.com.leroymerlin.estoque.entrypoint.rest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import br.com.leroymerlin.estoque.infra.jpa.repository.ProductJpaRepository;

@SpringBootTest
@ActiveProfiles("test")
class ProductRestControllerTest {

	@Autowired
	private WebApplicationContext webApplicationContext;

	@MockBean
	private ProductJpaRepository repository;

	@Test
	public void whenFileUploadedThenVerifyStatus() throws Exception {
		var contentStream = this.getClass().getClassLoader().getResourceAsStream("products.csv");
		var file = new MockMultipartFile("file", "file", "text/csv", contentStream);

		var mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
		mockMvc.perform(multipart("/products").file(file)).andExpect(status().isOk());

		Mockito.verify(repository).save(Mockito.any());
	}

}
