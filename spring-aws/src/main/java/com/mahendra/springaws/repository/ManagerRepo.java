package com.mahendra.springaws.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mahendra.springaws.entity.Manager;

@Repository
public interface ManagerRepo extends JpaRepository<Manager, Integer>{


}
