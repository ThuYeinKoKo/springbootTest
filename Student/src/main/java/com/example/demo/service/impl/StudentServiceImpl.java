package com.example.demo.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import com.example.demo.dao.StudentJpaRepository;
import com.example.demo.dto.StudentDto;
import com.example.demo.model.Student;
import com.example.demo.service.StudentService;

@Service
public class StudentServiceImpl implements StudentService{
	
	@Autowired
	StudentJpaRepository stuJpaRepository;

	@Override
	public List<StudentDto> getAllStudents() {
		
		List<Student> students = stuJpaRepository.findAll();
		
		List<StudentDto> dtos = new ArrayList<StudentDto>();
		for(Student stu : students) {
			StudentDto dto = new StudentDto(stu);
			dtos.add(dto);
		}
		return dtos;
	}

	@Override
	//@PreAuthorize("hasRole('ROLE_USER')")
	public StudentDto getStudentById(Long id) {
		Student student = stuJpaRepository.getOne(id);
		return new StudentDto(student);
	}

	@Override
	public StudentDto createNewStudent(StudentDto dto) {
		Student stu = dto.getEntity();
		Student saveStudent = stuJpaRepository.save(stu);
		return new StudentDto(saveStudent);
	}

	@Override
	public StudentDto updateStudent(StudentDto dto) {
		Student stu = dto.getEntity();
		Student saveStudent = stuJpaRepository.save(stu);
		return new StudentDto(saveStudent);
	}

	@Override
	public void deleteItemById(Long id) {
		stuJpaRepository.deleteById(id);
	}

}
