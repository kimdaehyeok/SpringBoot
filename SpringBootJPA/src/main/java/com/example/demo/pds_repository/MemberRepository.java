package com.example.demo.pds_repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.pdsboard.Member;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> 
{
	@Query("SELECT m.uid, count(p) FROM Member m LEFT OUTER JOIN Profile p ON m.uid = p.member WHERE m.uid = ?1 GROUP BY m")
	public List<Object[]> getMemberWithProfileCount(String uid);
	
	@Query("SELECT memb.uid, pro.fname FROM Member memb LEFT OUTER JOIN Profile pro ON memb.uid = pro.member WHERE memb.uid = ?1 AND pro.current = true")
	public List<Object[]> getMemberUseProfileInfo(String uid);
}
