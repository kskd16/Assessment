package com.lpu.spring_assignment;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableMethodSecurity
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) {
		
		http.cors(c->{});
		http.csrf(c -> c.disable());
		http.authorizeHttpRequests((req)->
		req.requestMatchers("/student**").permitAll().anyRequest().authenticated());
		//.requestMatchers("/student/*").hasAnyRole("ADMIN","USER"));
		
		http.formLogin(Customizer.withDefaults());
		http.httpBasic(Customizer.withDefaults());
		return http.build();
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedHeaders(List.of("*"));
		config.setAllowedMethods(List.of("GET","PUT","POST","DELETE"));
		config.setAllowCredentials(true);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", config);
		return source;
	}

}
