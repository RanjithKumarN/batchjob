package com.rest.api.BatchProcessor.component;

import java.io.IOException;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class ApiKeyAuthFilter extends OncePerRequestFilter {

	@Value("${api.key.value}")
    private String apiKey;
	
	@Value("${api.key.header}")
    private String apiHeader;


	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String apiKey = request.getHeader(apiHeader);

		if (apiKey.equals(apiKey)) {
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("apikeyUser", null,
					Collections.emptyList());
			SecurityContextHolder.getContext().setAuthentication(auth);
		} else {
			response.setStatus(HttpServletResponse.SC_FORBIDDEN);
			response.getWriter().write("Invalid API Key");
			return;
		}

		filterChain.doFilter(request, response);
	}
}