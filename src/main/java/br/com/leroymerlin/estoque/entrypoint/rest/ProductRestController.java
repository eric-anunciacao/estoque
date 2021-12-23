package br.com.leroymerlin.estoque.entrypoint.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.leroymerlin.estoque.usecase.DeleteProductUseCase;
import br.com.leroymerlin.estoque.usecase.FindAllProductsUseCase;
import br.com.leroymerlin.estoque.usecase.FindProductByIdUseCase;
import br.com.leroymerlin.estoque.usecase.PartialUpdateProductUseCase;
import br.com.leroymerlin.estoque.usecase.UpdateProductUseCase;
import br.com.leroymerlin.estoque.usecase.request.PartialUpdateProductRequest;
import br.com.leroymerlin.estoque.usecase.request.UpdateProductRequest;

@RestController
@RequestMapping("/products")
public class ProductRestController {

	private final FindAllProductsUseCase findAllProductsUseCase;
	private final FindProductByIdUseCase findProductByIdUseCase;
	private final DeleteProductUseCase deleteProductUseCase;
	private final UpdateProductUseCase updateProductUseCase;
	private final PartialUpdateProductUseCase partialUpdateProductUseCase;

	public ProductRestController(final FindAllProductsUseCase findAllProductsUseCase,
			final FindProductByIdUseCase findProductByLmUseCase, final DeleteProductUseCase deleteProductUseCase,
			final UpdateProductUseCase updateProductUseCase,
			final PartialUpdateProductUseCase partialUpdateProductUseCase) {
		this.findAllProductsUseCase = findAllProductsUseCase;
		this.findProductByIdUseCase = findProductByLmUseCase;
		this.deleteProductUseCase = deleteProductUseCase;
		this.updateProductUseCase = updateProductUseCase;
		this.partialUpdateProductUseCase = partialUpdateProductUseCase;
	}

	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll() {
		return ResponseEntity.ok(this.findAllProductsUseCase.findAll());
	}

	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.findProductByIdUseCase.findById(id));
	}

	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		this.deleteProductUseCase.delete(id);
		return ResponseEntity.ok().build();
	}

	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAll(@PathVariable("id") Long id,
			@RequestBody final UpdateProductRequest request) {
		request.setLm(id);
		return ResponseEntity.ok(this.updateProductUseCase.update(request));
	}

	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePart(@PathVariable("id") Long id,
			@RequestBody final PartialUpdateProductRequest request) {
		request.setLm(id);
		this.partialUpdateProductUseCase.partialUpdate(request);
		return ResponseEntity.noContent().build();
	}

}
