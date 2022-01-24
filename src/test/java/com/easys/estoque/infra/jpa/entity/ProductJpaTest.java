package com.easys.estoque.infra.jpa.entity;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import com.easys.estoque.utils.TestUtils;

class ProductJpaTest {

	@Test
	void shouldCreateProductJpa() {
		var jpa = TestUtils.getProductJpa();
		var other = TestUtils.getProductJpa();
		other.setLm(999L);

		assertTrue(jpa.hashCode() > 0);
		assertFalse(jpa.equals(other));
	}

}
