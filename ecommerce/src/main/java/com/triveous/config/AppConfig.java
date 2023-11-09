package com.triveous.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class AppConfig {

	@Bean
	public SecurityFilterChain springSecurityConfiguration(HttpSecurity http) throws Exception {

		http
		.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and()
		.csrf().disable()
		.authorizeHttpRequests()
		.requestMatchers(HttpMethod.POST, "/user/**")
		.permitAll()
		.requestMatchers(HttpMethod.GET, "/category/**").permitAll()
		.requestMatchers(HttpMethod.GET, "/product/**").permitAll()
		.requestMatchers(HttpMethod.POST,"/category/add").hasRole("ADMIN")
		.requestMatchers(HttpMethod.POST,"/product/**").hasRole("ADMIN")
		.requestMatchers(HttpMethod.POST,"/cart/**").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.GET,"/cart/**").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.PUT,"/cart/**").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.DELETE,"/cart/**").hasAnyRole("ADMIN","USER")
		.requestMatchers(HttpMethod.POST,"/orders/**").hasAnyRole("ADMIN","USER")
		.anyRequest()
		.authenticated()
		.and()
		.addFilterAfter(new JwtTokenGeneratorFilter(), BasicAuthenticationFilter.class)
		.addFilterBefore(new JwtTokenValidatorFilter(), BasicAuthenticationFilter.class)
		.formLogin()
		.and()
		.httpBasic();

		return http.build();

	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();

	}
}
