package br.com.leroymerlin.estoque.usecase.request;

import java.math.BigDecimal;

public class PartialUpdateProductRequest {

	private Long lm;
	private String name;
	private Boolean freeShipping;
	private String description;
	private BigDecimal price;
	private String category;

	public PartialUpdateProductRequest(String name, Boolean freeShipping, String description, BigDecimal price,
			String category) {
		this.name = name;
		this.freeShipping = freeShipping;
		this.description = description;
		this.price = price;
		this.category = category;
	}

	public Long getLm() {
		return lm;
	}

	public void setLm(Long lm) {
		this.lm = lm;
	}

	public String getName() {
		return name;
	}

	public Boolean getFreeShipping() {
		return freeShipping;
	}

	public String getDescription() {
		return description;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public String getCategory() {
		return category;
	}

}
