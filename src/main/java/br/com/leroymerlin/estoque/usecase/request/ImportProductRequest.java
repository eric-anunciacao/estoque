package br.com.leroymerlin.estoque.usecase.request;

import java.math.BigDecimal;

import com.opencsv.bean.CsvBindByName;

public class ImportProductRequest {

	@CsvBindByName
	private Long lm;

	@CsvBindByName
	private String name;

	@CsvBindByName(column = "free_shipping")
	private boolean freeShipping;

	@CsvBindByName
	private String description;

	@CsvBindByName
	private BigDecimal price;

	@CsvBindByName
	private String category;

	public Long getLm() {
		return lm;
	}

	public void setLm(Long lm) {
		this.lm = lm;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isFreeShipping() {
		return freeShipping;
	}

	public void setFreeShipping(boolean freeShipping) {
		this.freeShipping = freeShipping;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

}
