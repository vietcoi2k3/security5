package com.example.security5.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.security5.Service.UserService;





@EnableWebSecurity
@Configuration
public class config extends  WebSecurityConfigurerAdapter {
	@Bean
    public RestAuthenticationEntryPoint restServicesEntryPoint() {
        return new RestAuthenticationEntryPoint();
    }
	
	@Bean
	public CustomAccessDeniedHandler getCustomAccessDeniedHandler() {
		return new CustomAccessDeniedHandler();
	}
	
	 @Bean
	    public JwtAuthenticationFilter jwtAuthenticationFilter() {
	        return new JwtAuthenticationFilter();
	    }
	
	
	  @Override
	    protected void configure(HttpSecurity http) throws Exception {
		  	http.csrf().ignoringAntMatchers("/**").and()
	        
	                .authorizeRequests()
	                .antMatchers("/auth/**").permitAll();
	                http
	                .antMatcher("/**").httpBasic().
	                authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
	                .and()
	                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and().authorizeRequests()
	                .antMatchers(HttpMethod.GET, "/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
	                .antMatchers(HttpMethod.POST, "/**").access("hasRole('ROLE_USER')")
	                .antMatchers(HttpMethod.DELETE, "/**").access("hasRole('ROLE_ADMIN')").and()
	                .addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
	                
	                
	                
	               
	    
	         
//	        http.authorizeRequests().antMatchers(HttpMethod.POST, "/auth/**").permitAll();
	    }
	  
//	  @Override
//	  public void configure(WebSecurity web) throws Exception {
//	      web.ignoring().antMatchers("/ping**");
//	  }
	  
	  @Bean
	  public PasswordEncoder passwordEncoder() {
		  return new BCryptPasswordEncoder(); 
	  }
	   
}
