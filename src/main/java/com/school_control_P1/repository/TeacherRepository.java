package com.school_control_P1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.school_control_P1.model.Teacher;

public interface TeacherRepository extends JpaRepository<Teacher, Integer>{
	
}
