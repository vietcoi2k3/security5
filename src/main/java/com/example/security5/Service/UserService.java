package com.example.security5.Service;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.security5.Model.User;
import com.example.security5.dto.registerRequestDto;
import com.example.security5.repository.UserRepository;

@Service
public class UserService implements UserDetailsService{

	@Autowired
	UserRepository userRepository;
	
	@Autowired
	JwtService jwtService;
	
	@Autowired
	PasswordEncoder passwordEncoder;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		return userRepository.findByUsername(username);
	}
	
	public registerRequestDto register(registerRequestDto user) {
	
	
		if(!user.getPassword().equals(user.getPassword()))
		{
			return null;
		}
		if(userRepository.checkUser(user.getUsername())>0) {
			return null;
		}
		User resultUser = new User();
		resultUser.setUsername(user.getUsername());
		resultUser.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(resultUser);
		return user;
		
	}
	
	public String login(User user) {
	
		User userAuth = userRepository.findUserByUsername(user.getUsername());
		
		if(userAuth!=null) {
			if(passwordEncoder.matches(user.getPassword(), userAuth.getPassword())) {
				return jwtService.generateTokenLogin(user.getUsername());
			}
		}
		
		return null;
	}
	
	public User getUser(String username) {
		return userRepository.findUserByUsername(username);
	}
}
