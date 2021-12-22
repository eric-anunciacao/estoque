package br.com.leroymerlin.estoque.infra.dataprovider;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Component;

import br.com.leroymerlin.estoque.domain.dto.SaveProductDto;
import br.com.leroymerlin.estoque.domain.entity.Product;
import br.com.leroymerlin.estoque.domain.gateway.ProductGateway;
import br.com.leroymerlin.estoque.infra.jpa.entity.mapper.ProductJpaMapper;
import br.com.leroymerlin.estoque.infra.jpa.entity.mapper.ProductMapper;
import br.com.leroymerlin.estoque.infra.jpa.repository.ProductJpaRepository;

@Component
class ProductDataProvider implements ProductGateway {

	private final ProductJpaRepository repository;

	public ProductDataProvider(ProductJpaRepository repository) {
		this.repository = repository;
	}

	@Override
	@Transactional
	public Product save(SaveProductDto dto) {
		var jpa = ProductJpaMapper.INSTANCE.toJpa(dto);
		return ProductMapper.INSTANCE.toProduct(this.repository.save(jpa));
	}

	@Override
	public List<Product> findAll() {
		return ProductMapper.INSTANCE.toProducts(this.repository.findAll());
	}

	@Override
	@Transactional
	public void delete(Long id) {
		this.repository.deleteById(id);
	}

	@Override
	public boolean exists(Long id) {
		return this.repository.findById(id).isPresent();
	}

	@Override
	public Optional<Product> findById(Long id) {
		var optional = this.repository.findById(id);

		if (optional.isPresent()) {
			return Optional.ofNullable(ProductMapper.INSTANCE.toProduct(optional.get()));
		} else {
			return Optional.empty();
		}
	}

}
