package br.com.leroymerlin.estoque.domain.gateway;

import java.util.List;
import java.util.Optional;

import br.com.leroymerlin.estoque.domain.dto.SaveProductDto;
import br.com.leroymerlin.estoque.domain.entity.Product;

public interface ProductGateway {

	Product save(SaveProductDto dto);

	List<Product> findAll();

	Optional<Product> findById(Long id);

	void delete(Long id);

	boolean exists(Long id);

}
