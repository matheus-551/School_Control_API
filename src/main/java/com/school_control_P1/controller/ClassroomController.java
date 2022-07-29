package com.school_control_P1.controller;

import java.util.List;
import java.util.Optional;

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

import com.school_control_P1.exception.BusinessRuleException;
import com.school_control_P1.model.Classroom;
import com.school_control_P1.service.ClassroomService;

@RestController
@RequestMapping("/api/classroom")
public class ClassroomController {
	
	@Autowired
	private ClassroomService classroomService;
	
	@GetMapping
	public ResponseEntity<List<Classroom>> listAll() {
		List<Classroom> classrooms = this.classroomService.listAllClassrooms();
		return ResponseEntity.ok(classrooms);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Classroom> findByClassroom(@PathVariable("id") Integer id) {
		Optional<Classroom> classroom = this.classroomService.findClassroomById(id);
		
		if (classroom.isPresent()) {
			return new ResponseEntity<Classroom>(classroom.get(), HttpStatus.OK);
		} else 
			return new ResponseEntity("Ocorreu um erro ao buscar a sala", HttpStatus.BAD_REQUEST);
	}
	
	@PostMapping
	public ResponseEntity<Classroom> createClassroom(@RequestBody Classroom classroom) {
		try {
			Classroom classroomSaved = this.classroomService.save(classroom);
			return new ResponseEntity<Classroom>(classroomSaved, HttpStatus.CREATED);
		} catch (BusinessRuleException e) {
			return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}
	
	@PutMapping("/{id}")
	public ResponseEntity editClassroom(
			@PathVariable("id") Integer id, 
			@RequestBody Classroom classroom) {
		
		return this.classroomService.findClassroomById(id).map( entity -> {
			try {
				Classroom classroomPreSaved = classroom;
				classroomPreSaved.setId(entity.getId());

				Classroom classroomSaved = this.classroomService.save(classroomPreSaved);
				return ResponseEntity.ok(classroomSaved);
			} catch (BusinessRuleException e) {
				return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
			}

		}).orElseGet( () ->
			new ResponseEntity("Ocorreu um erro ao Editar a sala", HttpStatus.BAD_REQUEST));
	}
}
