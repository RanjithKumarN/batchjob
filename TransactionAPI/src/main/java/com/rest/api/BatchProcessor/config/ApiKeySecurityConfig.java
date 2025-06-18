package com.rest.api.BatchProcessor.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.rest.api.BatchProcessor.component.ApiKeyAuthFilter;

@Configuration
public class ApiKeySecurityConfig {

	@Bean
	public ApiKeyAuthFilter apiKeyAuthFilter() {
		return new ApiKeyAuthFilter();
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(csrf -> csrf.disable())
				.authorizeHttpRequests(
						auth -> auth.requestMatchers("/public/**", "/login").permitAll().anyRequest().authenticated())
				.addFilterBefore(apiKeyAuthFilter(), UsernamePasswordAuthenticationFilter.class)
				.httpBasic(Customizer.withDefaults());

		return http.build();
	}
}