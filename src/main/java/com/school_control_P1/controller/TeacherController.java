package com.school_control_P1.controller;

import java.util.List;
import java.util.Optional;

import com.school_control_P1.exception.BusinessRuleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.school_control_P1.dto.TeacherDTO;
import com.school_control_P1.model.Classroom;
import com.school_control_P1.model.Teacher;
import com.school_control_P1.service.ClassroomService;
import com.school_control_P1.service.TeacherService;

@RestController
@RequestMapping("/api/teacher")
public class TeacherController {
	
	@Autowired
	private TeacherService teacherService;
	
	@Autowired
	private ClassroomService classroomService;
	
	private Teacher converterDtoToEntity(TeacherDTO teacherDTO) {
		Teacher teacher = new Teacher();

		teacher.setName(teacherDTO.getName());

		Optional<Classroom> classroom = this.classroomService
				.findClassroomById(teacherDTO.getIdClassroom());

		if (classroom.isPresent()) {
			teacher.setClassroom(classroom.get());
		} else
			teacher.setClassroom(null);

		return teacher;
	}
	
	@GetMapping
	public ResponseEntity<List<Teacher>> listAll() {
		List<Teacher> teachers = this.teacherService.listAllTeachers();
		return ResponseEntity.ok(teachers);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Teacher> findByTeacher(@PathVariable("id") Integer id) {
		Optional<Teacher> teacher = this.teacherService.findTeacherById(id);
		
		if(teacher.isPresent()) {
			return ResponseEntity.ok().body(teacher.get());
		} else 
			return new ResponseEntity("Ocorreu um erro ao buscar o professor", HttpStatus.BAD_REQUEST);
	}

	@PostMapping
	public ResponseEntity<Teacher> createTeacher(@RequestBody TeacherDTO teacherDto) {
		try {
			Teacher teacher = converterDtoToEntity(teacherDto);

			Teacher teacherSaved = this.teacherService.save(teacher);

			return new ResponseEntity<Teacher>(teacherSaved, HttpStatus.CREATED);
		} catch (BusinessRuleException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity editTeacher(
			@PathVariable("id") Integer id,
			@RequestBody TeacherDTO teacherDto) {
		
		return this.teacherService.findTeacherById(id).map( entity -> {
			try{
				Teacher teacherPreSaved = converterDtoToEntity(teacherDto);
				teacherPreSaved.setId(entity.getId());

				Teacher teacherSaved = this.teacherService.save(teacherPreSaved);
				return ResponseEntity.ok(teacherSaved);
			} catch (BusinessRuleException e) {
				return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}).orElseGet(() ->
				new ResponseEntity("Ocorreu um erro", HttpStatus.BAD_REQUEST));
	}
}
