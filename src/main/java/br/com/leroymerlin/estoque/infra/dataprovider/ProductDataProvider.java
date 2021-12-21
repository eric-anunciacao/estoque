package br.com.leroymerlin.estoque.infra.dataprovider;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.dto.SaveProductDto;
import br.com.leroymerlin.estoque.domain.gateway.ProductGateway;
import br.com.leroymerlin.estoque.infra.jpa.entity.mapper.ProductJpaMapper;
import br.com.leroymerlin.estoque.infra.jpa.repository.ProductJpaRepository;

@Component
class ProductDataProvider implements ProductGateway {

	private final ProductJpaRepository repository;

	public ProductDataProvider(ProductJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	public void save(SaveProductDto dto) {
		var jpa = ProductJpaMapper.INSTANCE.toJpa(dto);
		repository.save(jpa);
	}

}
