package com.example.demo.controller.rest;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.StudentDto;
import com.example.demo.dto.Token;
import com.example.demo.model.User;
import com.example.demo.service.impl.UserService;

@RestController
//@Controller
@CrossOrigin
@RequestMapping(path = "/api/user")
public class AuthController {
	  @Autowired
	  private UserService userService;

	
	  @GetMapping()
	  public String getTest() 
	  {
		  System.out.println("In getTest");
	       return "test";
	  }
	  

	  @PostMapping("/login")
	  public Token login(@Valid @RequestBody User user) {
		Token token = new Token();
		String tok = userService.login(user.getName(), user.getPassword());
		token.setToken(tok);
		System.out.println("In Login -token- "+ token);
	    return token;
	  }

	  @PostMapping("/register")
	  public String register(@Valid @RequestBody User user) {
		  String str = userService.register(user);
		  System.out.println("In register - "+ str);
	    return str;
	  }
}
