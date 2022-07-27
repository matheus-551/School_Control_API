package com.school_control_P1.service;

import java.util.List;
import java.util.Optional;

import com.school_control_P1.exception.BusinessRuleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school_control_P1.model.Teacher;
import com.school_control_P1.repository.TeacherRepository;

@Service
public class TeacherService {
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	public Teacher save(Teacher teacher) {

		if (teacher.getName() == null || teacher.getName().trim().equals("")) {
			throw new BusinessRuleException("Informe um nome válido !");
		}
		
		return this.teacherRepository.save(teacher);
	}
	
	public List<Teacher> listAllTeachers() {
		return this.teacherRepository.findAll();
	}
	
	public Optional<Teacher> findTeacherById(Integer id) {
		return this.teacherRepository.findById(id);
	}
}
