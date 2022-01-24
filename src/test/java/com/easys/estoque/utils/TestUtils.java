package com.easys.estoque.utils;

import java.math.BigDecimal;

import com.easys.estoque.infra.jpa.entity.ProductJpa;

public class TestUtils {

	public static ProductJpa getProductJpa() {
		var jpa = new ProductJpa();
		jpa.setLm(1003L);
		jpa.setName("Chave de Fenda X");
		jpa.setDescription("Chave de fenda simples");
		jpa.setPrice(BigDecimal.TEN);
		jpa.setCategory("123123");
		return jpa;
	}

}
