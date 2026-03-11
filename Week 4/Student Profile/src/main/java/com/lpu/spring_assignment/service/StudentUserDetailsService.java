package com.lpu.spring_assignment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.lpu.spring_assignment.entity.Student;
import com.lpu.spring_assignment.repo.StudentRepository;


@Service
public class StudentUserDetailsService implements UserDetailsService {

		@Autowired
		private StudentRepository repo;

		@Override
		public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
			Student student = repo.findByEmail(username);
			return new StudentUserDetails(student);
		}

	

}
