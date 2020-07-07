package com.mini.SpringBatchExample.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mini.SpringBatchExample.entty.Users;

@Repository
public interface UserRepository extends JpaRepository<Users, Integer> {

}
