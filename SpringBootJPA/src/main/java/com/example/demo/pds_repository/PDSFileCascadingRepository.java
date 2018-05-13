package com.example.demo.pds_repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.pdsboard.PDSBoardCascading;

public interface PDSFileCascadingRepository extends CrudRepository<PDSBoardCascading, Long> 
{
	@Query("SELECT p.pname, count(*) FROM PDSBoard p LEFT OUTER JOIN p.files f ON p.pid = f WHERE p.pid > 0 GROUP BY p ORDER BY p.pid DESC")
	public List<Object[]> getSummary();
}
