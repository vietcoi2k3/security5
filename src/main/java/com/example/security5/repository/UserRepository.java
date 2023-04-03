package com.example.security5.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import com.example.security5.Model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

	@Query(value = "select * from user where username =:username",nativeQuery = true)
	public UserDetails findByUsername(String username);
	
	@Query(value = "select count(id) from user where username =:username and password =:password",nativeQuery = true)
	public int checkLogin(String username,String password);
	
	@Query(value = "select count(id) from user where username =:username",nativeQuery = true)
	public int checkUser(String username);
	
	@Query(value = "select password from user where username =:username",nativeQuery = true)
	public String findPassByUsername(String username);
	
	@Query(value = "select * from user where username =:username  ",nativeQuery = true)
	public User findUserByUsername(String username);
}
