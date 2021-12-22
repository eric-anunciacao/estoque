package br.com.leroymerlin.estoque.usecase.request;

import java.math.BigDecimal;

public class UpdateProductRequest {

	private Long lm;
	private String name;
	private boolean freeShipping;
	private String description;
	private BigDecimal price;
	private String category;

	public UpdateProductRequest(String name, boolean freeShipping, String description, BigDecimal price,
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

	public boolean isFreeShipping() {
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
