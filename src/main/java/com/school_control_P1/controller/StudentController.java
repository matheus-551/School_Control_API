package com.school_control_P1.controller;

import com.school_control_P1.dto.StudentDTO;
import com.school_control_P1.exception.BusinessRuleException;
import com.school_control_P1.model.Student;
import com.school_control_P1.model.Teacher;
import com.school_control_P1.service.StudentService;
import com.school_control_P1.service.TeacherService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @Autowired
    private TeacherService teacherService;

    private Student converterDtoToEntity(StudentDTO studentDTO) {
        Student student = new Student();

        Optional<Teacher> teacher = this.teacherService.findTeacherById(studentDTO.getIdTeacher());

        if (teacher.isPresent()) {
            student.setTeacher(teacher.get());
        } else
            student.setTeacher(null);

        student.setName(studentDTO.getName());

        return student;
    }

    @GetMapping
    public ResponseEntity<List<Student>> listAll() {
        List<Student> students = this.studentService.listAllStudents();
        return ResponseEntity.ok(students);
    }

    @PostMapping
    public ResponseEntity<Student> createStudent(@RequestBody StudentDTO studentDto) {
        try {
            Student student = converterDtoToEntity(studentDto);

            Student studentSaved = this.studentService.save(student);

            return new ResponseEntity<Student>(studentSaved, HttpStatus.CREATED);
        } catch (BusinessRuleException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity editStudent(
            @PathVariable("id") Integer id,
            @RequestBody StudentDTO studentDto) {

        return this.studentService.findStudentById(id).map( entity -> {
            try {
                Student studentPreSaved = converterDtoToEntity(studentDto);
                studentPreSaved.setId(entity.getId());

                Student studentSaved = this.studentService.save(studentPreSaved);
                return ResponseEntity.ok(studentSaved);
            } catch (BusinessRuleException e) {
                return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
            }
        }).orElseGet(() ->
                new ResponseEntity("Ocorreu um erro", HttpStatus.BAD_REQUEST));
    }
}
