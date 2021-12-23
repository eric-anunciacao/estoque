package br.com.leroymerlin.estoque.entrypoint.rest;

import java.io.IOException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.leroymerlin.estoque.cross.CSVUtils;
import br.com.leroymerlin.estoque.usecase.FindFileStatusUseCase;
import br.com.leroymerlin.estoque.usecase.ImportProductUseCase;
import br.com.leroymerlin.estoque.usecase.LogFileImportUseCase;
import br.com.leroymerlin.estoque.usecase.request.ImportFileRequest;
import br.com.leroymerlin.estoque.usecase.request.ImportProductRequest;
import br.com.leroymerlin.estoque.usecase.response.ImportProductResponse;

@RestController
@RequestMapping("/files")
public class FileRestController {

	private final ImportProductUseCase importProductUseCase;
	private final LogFileImportUseCase logFileImportUseCase;
	private final FindFileStatusUseCase findFileStatusUseCase;

	public FileRestController(ImportProductUseCase importProductUseCase, LogFileImportUseCase logFileImportUseCase,
			final FindFileStatusUseCase findFileStatusUseCase) {
		this.importProductUseCase = importProductUseCase;
		this.logFileImportUseCase = logFileImportUseCase;
		this.findFileStatusUseCase = findFileStatusUseCase;
	}

	@PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> upload(@RequestParam("file") MultipartFile file) throws IOException {
		var request = CSVUtils.convert(file.getInputStream(), ImportProductRequest.class);

		var records = request.stream().count();
		var fileRequest = new ImportFileRequest(file.getOriginalFilename(), records);
		var fileId = this.logFileImportUseCase.log(fileRequest);

		this.importProductUseCase.execute(fileId, request);
		return ResponseEntity.status(HttpStatus.CREATED).body(new ImportProductResponse(fileId, records));
	}

	@GetMapping(path = "/{id}/status", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Object> status(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.findFileStatusUseCase.status(id));
	}
}