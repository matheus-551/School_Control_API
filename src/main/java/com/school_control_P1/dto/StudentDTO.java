package com.school_control_P1.dto;

public class StudentDTO {

    private String name;
    private Integer idTeacher;

    public StudentDTO() {

    }

    public StudentDTO(String name, Integer idTeacher) {
        this.name = name;
        this.idTeacher = idTeacher;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Integer idTeacher) {
        this.idTeacher = idTeacher;
    }
}
