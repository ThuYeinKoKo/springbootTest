package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.StudentDto;

public interface StudentService {

	List<StudentDto> getAllStudents();
	
	StudentDto getStudentById(Long id);
	
	StudentDto createNewStudent(StudentDto dto);
	
	StudentDto updateStudent(StudentDto dto);
	
	void deleteItemById(Long id);
}
