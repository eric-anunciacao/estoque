package com.easys.estoque.entrypoint.rest;

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

import com.easys.estoque.usecase.DeleteProductUseCase;
import com.easys.estoque.usecase.FindAllProductsUseCase;
import com.easys.estoque.usecase.FindProductByIdUseCase;
import com.easys.estoque.usecase.PartialUpdateProductUseCase;
import com.easys.estoque.usecase.UpdateProductUseCase;
import com.easys.estoque.usecase.request.PartialUpdateProductRequest;
import com.easys.estoque.usecase.request.UpdateProductRequest;
import com.easys.estoque.usecase.response.ProductResponse;
import com.easys.estoque.usecase.response.UpdateProductResponse;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

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

	@Operation(summary = "Consultar a lista de produtos cadastrados")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Produtos cadastrados", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(allOf = ProductResponse.class)) }),
			@ApiResponse(responseCode = "204", description = "Nenhum produto cadastrado no banco de dados", content = @Content) })
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findAll() {
		return ResponseEntity.ok(this.findAllProductsUseCase.findAll());
	}

	@Operation(summary = "Consultar o produto pelo seu c??digo (Lm)")
	@ApiResponses(value = { @ApiResponse(responseCode = "200", description = "Detalhes do Produto", content = {
			@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = ProductResponse.class)) }),
			@ApiResponse(responseCode = "204", description = "Produto n??o encontrado para o ID informado", content = @Content) })
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> findById(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.findProductByIdUseCase.findById(id));
	}

	@Operation(summary = "Excluir um produto a partir do seu ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Produto exclu??do com sucesso", content = @Content),
			@ApiResponse(responseCode = "404", description = "Produto n??o encontrado para o ID informado", content = @Content) })
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> delete(@PathVariable("id") Long id) {
		this.deleteProductUseCase.delete(id);
		return ResponseEntity.noContent().build();
	}

	@Operation(summary = "Atualizar todos os atributos do produto pelo seu c??digo (Lm)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso", content = {
					@Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = UpdateProductResponse.class)) }),
			@ApiResponse(responseCode = "404", description = "Produto n??o encontrado para o ID informado", content = @Content) })
	@PutMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updateAll(@PathVariable("id") Long id,
			@RequestBody final UpdateProductRequest request) {
		request.setLm(id);
		return ResponseEntity.ok(this.updateProductUseCase.update(request));
	}

	@Operation(summary = "Atualizar atributo(s) espec??fico(s) do produto pelo seu c??digo (Lm)")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Produto atualizado com sucesso", content = @Content),
			@ApiResponse(responseCode = "404", description = "Produto n??o encontrado para o ID informado", content = @Content) })
	@PatchMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> updatePart(@PathVariable("id") Long id,
			@RequestBody final PartialUpdateProductRequest request) {
		request.setLm(id);
		this.partialUpdateProductUseCase.partialUpdate(request);
		return ResponseEntity.noContent().build();
	}

}
