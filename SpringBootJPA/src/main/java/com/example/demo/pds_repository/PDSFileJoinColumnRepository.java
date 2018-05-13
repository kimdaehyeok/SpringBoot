package com.example.demo.pds_repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.pdsboard.PDSBoardJoinColumnAnnotation;

/* One에 해당하는 PDSBoard에 대해서만 Repository를 생성해 준다.
 * */
public interface PDSFileJoinColumnRepository extends CrudRepository<PDSBoardJoinColumnAnnotation, Long> 
{

}
