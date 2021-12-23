package br.com.leroymerlin.estoque.usecase.response;

public class FindFileStatusResponse {

	private Long fileId;
	private String status;

	public FindFileStatusResponse(Long fileId, String status) {
		this.fileId = fileId;
		this.status = status;
	}

	public Long getFileId() {
		return fileId;
	}

	public String getStatus() {
		return status;
	}

}
