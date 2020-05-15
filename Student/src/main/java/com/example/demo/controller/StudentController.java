package com.example.demo.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentDto;
import com.example.demo.service.StudentService;

@Controller
//@RestController
@RequestMapping("/student")
public class StudentController {

	@Autowired
	StudentService stuService;
	
	/*@GetMapping()
	String show(Model model) {
		System.out.println("student show");
		//List<StudentDto> students = stuService.getAllStudents();
		model.addAttribute("students", "Mg Mg");
		return "test";
	}*/
	
	@GetMapping("/list")
	public String studentList(Model model) {
		System.out.println("student list");
		List<StudentDto> students = stuService.getAllStudents();
		for(StudentDto stu : students) {
			System.out.println("Id: "+stu.getStuId());
			System.out.println("Name: "+stu.getStudentName());
			System.out.println("Email: "+stu.getStudentEmail());
		}
		
		model.addAttribute("students", students);
		return "student/students";
	}
	
	@GetMapping("/new")
	public String newStudent(Model model) {
		System.out.println("in create student with Post");
		StudentDto student = new StudentDto();
		System.out.println("student id: "+student.getStuId());
		System.out.println("student name: "+student.getStudentName());
		System.out.println("student email: "+student.getStudentEmail());
		
		model.addAttribute("student", student);
		return "student/newstudent";
	}
	
	@PostMapping("/new")
	public String createStudent(@Valid StudentDto student,Errors error) {
		System.out.println("in create student with Post");
		if(error.hasErrors()) {
			return "student/newstudent";
		}else {
			System.out.println("student id: "+student.getStuId());
			System.out.println("student name: "+student.getStudentName());
			System.out.println("student email: "+student.getStudentEmail());
			stuService.createNewStudent(student);
			return "redirect:/student/list";
		}
		
	}
	
	@GetMapping("/edit/{id}")
	public String editStudent(@PathVariable Long id,Model model) {
		System.out.println("in Edit Student");
		
		StudentDto student = stuService.getStudentById(id);
		System.out.println("student id: "+student.getStuId());
		System.out.println("student name: "+student.getStudentName());
		System.out.println("student email: "+student.getStudentEmail());
		
		model.addAttribute("student", student);
		
		return "student/newstudent";
	}
	
	@GetMapping("/delete/{id}")
	public String deleteStudent(@PathVariable Long id) {
		stuService.deleteItemById(id);
		return "redirect:/student/list";
	}
}
