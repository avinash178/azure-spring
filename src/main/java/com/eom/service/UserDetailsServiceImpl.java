package com.eom.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.eom.entity.UsersEntity;
import com.eom.repository.UserDataRepository;
import com.eom.security.MyUsersDatails;



@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDataRepository userRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		Optional<UsersEntity> user = userRepository.findUsersByUserName(username);
		if(user.isEmpty())throw new UsernameNotFoundException("username not found");
		
		return   new MyUsersDatails(user.get());
		
	}
	

}
