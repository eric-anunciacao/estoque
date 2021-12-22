package br.com.leroymerlin.estoque.infra.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.leroymerlin.estoque.infra.jpa.entity.FileJpa;

@Repository
public interface FileJpaRepository extends CrudRepository<FileJpa, Long> {

}
