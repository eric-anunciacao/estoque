package com.easys.estoque.usecase.request;

public class ImportFileRequest {

	private String filename;
	private long numberOfRecords;

	public ImportFileRequest(String filename, long numberOfRecords) {
		this.filename = filename;
		this.numberOfRecords = numberOfRecords;
	}

	public String getFilename() {
		return filename;
	}

	public long getNumberOfRecords() {
		return numberOfRecords;
	}

}
