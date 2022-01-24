package com.easys.estoque.usecase.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class UpdateProductResponse {

	private Long lm;
	private String name;
	private boolean freeShipping;
	private String description;
	private BigDecimal price;
	private String category;
	private LocalDateTime updatedAt;

	public UpdateProductResponse(Long lm, String name, boolean freeShipping, String description, BigDecimal price,
			String category, LocalDateTime updatedAt) {
		this.lm = lm;
		this.name = name;
		this.freeShipping = freeShipping;
		this.description = description;
		this.price = price;
		this.category = category;
		this.updatedAt = updatedAt;
	}

	public Long getLm() {
		return lm;
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

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

}
