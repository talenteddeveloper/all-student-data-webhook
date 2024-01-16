package com.allstudent.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.allstudent.data.model.SchoolData;

public interface SchoolDataRepository extends JpaRepository<SchoolData, Integer>{

}
