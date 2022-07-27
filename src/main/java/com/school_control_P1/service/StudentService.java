package com.school_control_P1.service;

import com.school_control_P1.exception.BusinessRuleException;
import com.school_control_P1.model.Student;
import com.school_control_P1.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository  studentRepository;

    public Student save(Student student) {

        if (student.getName() == null || student.getName().trim().equals("")) {
            throw new BusinessRuleException("Informe um nome válido");
        }

        return this.studentRepository.save(student);
    }

    public List<Student> listAllStudents() {
        return this.studentRepository.findAll();
    }

    public Optional<Student> findStudentById(Integer id) {
        return this.studentRepository.findById(id);
    }
}
