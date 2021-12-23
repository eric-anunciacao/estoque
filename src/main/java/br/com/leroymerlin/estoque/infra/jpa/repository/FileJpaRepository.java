package br.com.leroymerlin.estoque.infra.jpa.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.leroymerlin.estoque.infra.jpa.entity.FileJpa;

@Repository
public interface FileJpaRepository extends CrudRepository<FileJpa, Long> {

	@Modifying
	@Query("UPDATE FileJpa f SET f.persistedRecords = (NVL(f.persistedRecords, 0) + 1) WHERE f.id = :id")
	void incrementPersistedRecordsFor(@Param("id") Long id);

}
