package com.school_control_P1.dto;

public class TeacherDTO {

	private String name;
	private Integer idClassroom;

	public TeacherDTO() {

	}

	public TeacherDTO(String name, Integer idClassroom) {
		this.name = name;
		this.idClassroom = idClassroom;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getIdClassroom() {
		return idClassroom;
	}

	public void setIdClassroom(Integer idClassroom) {
		this.idClassroom = idClassroom;
	}
}
