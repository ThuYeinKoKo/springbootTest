package com.example.demo.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserJpaRepository;
import com.example.demo.model.User;
import com.example.demo.security.JwtTokenProvider;


@Service
public class UserService {

  @Autowired
  private UserJpaRepository userRepository;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private JwtTokenProvider jwtTokenProvider;

  @Autowired
  private AuthenticationManager authenticationManager;

  public String login(String username, String password) {
    try {
    	System.out.println("username: "+username);
    	System.out.println("password: "+password);
      authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
      return jwtTokenProvider.createToken(username, userRepository.findByName(username).getRoles());
    } catch (AuthenticationException e) {
    	e.printStackTrace();
      throw new RuntimeException("Invalid username/password supplied");
    }
  }

  public String register(User user) {
	  System.out.println("name : "+ user.getName());
	  System.out.println("password : "+ user.getPassword());
	  System.out.println("email : "+ user.getEmail());
	  System.out.println("roles : "+ user.getRoles());
	  User another = this.userRepository.findByName(user.getName());
	  System.out.println("User another "+another);
	  if(another == null)
	  {
		  String encryptPassword = passwordEncoder.encode(user.getPassword());
		  System.out.println("encrypt password: "+encryptPassword);
		  user.setPassword(encryptPassword);
		  System.out.println("After set Password");
	      userRepository.save(user);
	      System.out.println("After save in db");
	      System.out.println("user roles : "+ user.getRoles());
	      return jwtTokenProvider.createToken(user.getName(), user.getRoles());
	  }
	  else
	  {
		  throw new RuntimeException("User name already exist");
	  }	  
	  
  }

  

}
