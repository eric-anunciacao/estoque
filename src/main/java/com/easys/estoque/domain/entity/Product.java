package com.easys.estoque.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Product {

	private Long lm;
	private String name;
	private boolean freeShipping;
	private String description;
	private BigDecimal price;
	private String category;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;

	public Product(Long lm, String name, boolean freeShipping, String description, BigDecimal price, String category,
			LocalDateTime createdAt, LocalDateTime updatedAt) {
		this.lm = lm;
		this.name = name;
		this.freeShipping = freeShipping;
		this.description = description;
		this.price = price;
		this.category = category;
		this.createdAt = createdAt;
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

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

}
