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

	@Query("SELECT CASE WHEN f.totalRecords = f.persistedRecords THEN 'PROCESSED' "
			+ "WHEN f.totalRecords > f.persistedRecords AND size(f.problems) > 0 THEN 'PROCESSED_WITH_ERROR' "
			+ "ELSE 'PROCESSING' END FROM FileJpa f WHERE f.id = :id")
	String getStatus(@Param("id") Long id);

}
