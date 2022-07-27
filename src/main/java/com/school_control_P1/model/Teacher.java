package com.school_control_P1.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Teacher {
	
	@Id
	@GeneratedValue(generator = "increment")
	@GenericGenerator(name = "increment", strategy = "increment")
	private Integer id;
	@Column(length = 150, nullable = false)
	private String name;
	@ManyToOne
	private Classroom classroom;
	
	public Teacher() {
		
	}
	
	public Integer getId() {
		return this.id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return this.name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public Classroom getClassroom() {
		return this.classroom;
	}
	
	public void setClassroom(Classroom classroom) {
		this.classroom = classroom;
	}

	@Override
	public String toString() {
		return "Teacher [id=" + id + ", name=" + name + ", classroom=" + classroom + "]";
	}
	
}
