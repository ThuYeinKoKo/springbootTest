package com.example.demo.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentDto;
import com.example.demo.service.StudentService;

@RestController
@RequestMapping(path = "/api/student")
public class StudentApi {

	@Autowired
	StudentService stuService;
	
	@GetMapping(produces = "application/json")
    public List<StudentDto> getAllStudent() 
    {
		System.out.println("in api/student getAllStudent");
       return stuService.getAllStudents();
    }
	
	@GetMapping(path="/{id}",produces = "application/json")
    public StudentDto getStudent(@PathVariable("id") Long id) 
    {
		System.out.println("in api/student -id: "+id);
       return stuService.getStudentById(id);
    }
	
	@PostMapping
    public StudentDto createNewStudent(@RequestBody StudentDto stuDto) 
    {
		System.out.println("Create New Student");
		System.out.println("stuDto Name: "+stuDto.getStudentName());
		System.out.println("stuDto Email: "+stuDto.getStudentEmail());
		return stuService.createNewStudent(stuDto);
    }
	
	@PutMapping(path="/{id}")
    public StudentDto updateStudent(@PathVariable("id") Long id,@RequestBody StudentDto stuDto) 
    {
		System.out.println("Update  Student id: "+ id);
		System.out.println("stuDto Name: "+stuDto.getStudentName());
		System.out.println("stuDto Email: "+stuDto.getStudentEmail());
		
		stuDto.setStuId(id);
        return stuService.createNewStudent(stuDto);
    }
	
	 @DeleteMapping(path="/{id}")
     public void deleteStudent(@PathVariable("id") Long id) 
     {
		 System.out.println("Delete  Student id: "+ id);
         stuService.deleteItemById(id);
     }
}
