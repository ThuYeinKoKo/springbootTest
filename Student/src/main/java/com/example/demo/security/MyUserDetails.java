package com.example.demo.security;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.dao.UserJpaRepository;

@Service
public class MyUserDetails implements UserDetailsService {

  @Autowired
  private UserJpaRepository userJpaRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	  final User user = userJpaRepository.findByName(username);
	  System.out.println("Find by username "+username +" Got user "+user);
	  if (user == null) {
	      throw new UsernameNotFoundException("User '" + username + "' not found");
	    }
	  
	  //Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
	  /*List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
      for (Role role : user.getRoles()){
          grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));
      }*/
	  List<GrantedAuthority> grantedAuthorities = new ArrayList<GrantedAuthority>();
      for (Role role : user.getRoles()){
          grantedAuthorities.add(new SimpleGrantedAuthority(role.getAuthority()));
      }
      
      

      //return new org.springframework.security.core.userdetails.User(user.getName(), user.getPassword(), grantedAuthorities);
	  return org.springframework.security.core.userdetails.User//
		        .withUsername(username)//
		        .password(user.getPassword())//
		        .authorities(grantedAuthorities)//
		        .accountExpired(false)//
		        .accountLocked(false)//
		        .credentialsExpired(false)//
		        .disabled(false)//
		        .build();

  	}
}
