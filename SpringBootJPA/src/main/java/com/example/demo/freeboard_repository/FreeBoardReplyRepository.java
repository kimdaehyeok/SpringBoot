package com.example.demo.freeboard_repository;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.domain.freeboard.FreeBoardReply;

public interface FreeBoardReplyRepository extends CrudRepository<FreeBoardReply, Long> 
{
	
}
