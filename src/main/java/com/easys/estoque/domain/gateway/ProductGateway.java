package com.easys.estoque.domain.gateway;

import java.util.List;
import java.util.Optional;

import com.easys.estoque.domain.dto.SaveProductDto;
import com.easys.estoque.domain.entity.Product;

public interface ProductGateway {

	Product save(SaveProductDto dto);

	List<Product> findAll();

	Optional<Product> findById(Long id);

	void delete(Long id);

	boolean exists(Long id);

}
