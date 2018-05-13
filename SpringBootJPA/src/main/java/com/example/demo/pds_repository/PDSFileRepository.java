package com.example.demo.pds_repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.pdsboard.PDSBoard;

public interface PDSFileRepository extends CrudRepository<PDSBoard, Long> 
{
	/* @Query는 기본적으로 select 구문만 지원하지만, @Modifying을 사용하면 DML(Insert, Delete, Update)를 처리할 수 있다.
	 * */
	@Modifying
	@Query("UPDATE FROM PDSFile f SET f.pdsfiles = ?2 WHERE f.fno = ?1")
	public int updatePDSFile(Long fno, String newFileName);

}
