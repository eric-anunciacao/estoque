package com.easys.estoque.usecase.response;

public class ImportProductResponse {

	private Long fileId;
	private long numberOfRecords;

	public ImportProductResponse(Long fileId, long numberOfRecords) {
		this.fileId = fileId;
		this.numberOfRecords = numberOfRecords;
	}

	public Long getFileId() {
		return fileId;
	}

	public long getNumberOfRecords() {
		return numberOfRecords;
	}

}
