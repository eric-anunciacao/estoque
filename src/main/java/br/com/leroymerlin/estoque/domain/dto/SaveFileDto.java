package br.com.leroymerlin.estoque.domain.dto;

public class SaveFileDto {

	private String name;
	private long totalRecords;
	private long persistedRecords;

	public SaveFileDto(String name, long totalRecords) {
		this.name = name;
		this.totalRecords = totalRecords;
	}

	public String getName() {
		return name;
	}

	public long getTotalRecords() {
		return totalRecords;
	}

	public long getPersistedRecords() {
		return persistedRecords;
	}

}
