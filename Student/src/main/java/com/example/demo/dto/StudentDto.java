package com.example.demo.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.example.demo.model.Student;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class StudentDto {
	
	Long stuId;
	
	@NotNull
	@Size(min = 5, message = "Name must be at least 5 characters long")
	String studentName;
	
	@NotNull
	@Size(min = 5, message = "Email must be at least 5 characters long")
	String studentEmail;

	public StudentDto() {
	}
	
	public StudentDto(Student student) {
		this.stuId = student.getStuId();
		this.studentName = student.getStuName();
		this.studentEmail = student.getStuEmail();
	}
	
	@JsonIgnore
	public Student getEntity() {
		Student stu = new Student();
		stu.setStuId(this.stuId);
		stu.setStuName(this.studentName);
		stu.setStuEmail(this.studentEmail);
		return stu;
	}

	public Long getStuId() {
		return stuId;
	}

	public void setStuId(Long stuId) {
		this.stuId = stuId;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public String getStudentEmail() {
		return studentEmail;
	}

	public void setStudentEmail(String studentEmail) {
		this.studentEmail = studentEmail;
	}
	
	
}
