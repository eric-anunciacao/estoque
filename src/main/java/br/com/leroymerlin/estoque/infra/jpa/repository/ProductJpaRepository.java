package br.com.leroymerlin.estoque.infra.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.leroymerlin.estoque.infra.jpa.entity.ProductJpa;

@Repository
public interface ProductJpaRepository extends CrudRepository<ProductJpa, Long> {

}
