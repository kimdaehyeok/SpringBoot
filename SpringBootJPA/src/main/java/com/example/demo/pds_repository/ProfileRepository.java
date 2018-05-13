package com.example.demo.pds_repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.domain.pdsboard.Profile;

@Repository
public interface ProfileRepository extends CrudRepository<Profile, Long> 
{

}
