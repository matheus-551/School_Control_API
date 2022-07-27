package com.school_control_P1.service;

import java.util.List;
import java.util.Optional;

import com.school_control_P1.exception.BusinessRuleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.school_control_P1.model.Classroom;
import com.school_control_P1.repository.ClassroomRepository;

@Service
public class ClassroomService {
	
	@Autowired
	private ClassroomRepository classRepository;
	
	public Classroom save(Classroom classroom) {

		if (classroom.getName() == null || classroom.getName().trim().equals("")) {
			throw new BusinessRuleException("Digite um nome válido para a sala");
		}

		return this.classRepository.save(classroom);
	}
	
	public List<Classroom> listAllClassrooms() {
		return this.classRepository.findAll();
	}
	
	public Optional<Classroom> findClassroomById(Integer id) {
		return this.classRepository.findById(id);
	}
}
