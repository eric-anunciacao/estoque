package br.com.leroymerlin.estoque.infra.jpa.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.leroymerlin.estoque.infra.jpa.entity.FileProblemJpa;

@Repository
public interface FileProblemJpaRepository extends CrudRepository<FileProblemJpa, Long> {

}
