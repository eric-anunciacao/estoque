package com.easys.estoque.domain.dto;

import java.math.BigDecimal;

import com.easys.estoque.cross.ObjectMapperUtils;
import com.easys.estoque.infra.config.exception.JSONConverterException;
import com.fasterxml.jackson.core.JsonProcessingException;

public class ImportProductDto {

	private Long lm;
	private String name;
	private boolean freeShipping;
	private String description;
	private BigDecimal price;
	private String category;

	public ImportProductDto(Long lm, String name, boolean freeShipping, String description, BigDecimal price,
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

	@Override
	public String toString() {
		try {
			return ObjectMapperUtils.MAPPER.writeValueAsString(this);
		} catch (JsonProcessingException e) {
			throw new JSONConverterException("Error converting to JSON", e);
		}
	}

}
