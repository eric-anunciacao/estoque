package br.com.leroymerlin.estoque.entrypoint.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.leroymerlin.estoque.cross.CSVUtils;
import br.com.leroymerlin.estoque.usecase.ImportProductUseCase;
import br.com.leroymerlin.estoque.usecase.request.ImportProductRequest;

@RestController
public class ProductRestController {

	private ImportProductUseCase importProductUseCase;

	public ProductRestController(ImportProductUseCase importProductUseCase) {
		this.importProductUseCase = importProductUseCase;
	}

	@PostMapping("/products")
	public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) {
		var request = CSVUtils.convert(file, ImportProductRequest.class);
		this.importProductUseCase.execute(request);
		return ResponseEntity.ok().build();
	}

}
