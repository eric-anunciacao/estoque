package com.easys.estoque.infra.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.easys.estoque.infra.jpa.entity.ProductJpa;

@Repository
public interface ProductJpaRepository extends JpaRepository<ProductJpa, Long> {

}
