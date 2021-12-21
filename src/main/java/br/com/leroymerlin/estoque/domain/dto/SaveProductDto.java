package br.com.leroymerlin.estoque.domain.dto;

import java.math.BigDecimal;

public class SaveProductDto {

	private Long lm;
	private String name;
	private boolean freeShipping;
	private String description;
	private BigDecimal price;
	private String category;

	public SaveProductDto(Long lm, String name, boolean freeShipping, String description, BigDecimal price,
			String category) {
		this.lm = lm;
		this.name = name;
		this.freeShipping = freeShipping;
		this.description = description;
		this.price = price;
		this.category = category;
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

}
