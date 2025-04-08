package com.seplag.servidores.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.seplag.servidores.dto.request.LoginRequest;
import com.seplag.servidores.security.util.CachedBodyHttpServletRequest;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JsonAuthenticationFilter extends OncePerRequestFilter {

    private final JsonAuthenticationManager authenticationManager;
    private final ObjectMapper objectMapper;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        // Motivos para usar CachedBodyHttpServletRequest: https://www.baeldung.com/spring-reading-httpservletrequest-multiple-times
        CachedBodyHttpServletRequest requestWrapper = new CachedBodyHttpServletRequest(request);

        // este filtro não se aplica
        if (!isPostMethod(requestWrapper)) {
            filterChain.doFilter(requestWrapper, response);
            return;
        }

        // este filtro não se aplica
        if (!isLoginPath(requestWrapper)) {
            filterChain.doFilter(requestWrapper, response);
            return;
        }

        LoginRequest requestBody;
        try {
            requestBody = parseBody(requestWrapper);
        } catch (IOException e) {
            filterChain.doFilter(requestWrapper, response);
            return;
        }

        log.info("Tentando autenticar usuário");

        Authentication preAuthentication = new UsernamePasswordAuthenticationToken(requestBody.username(), requestBody.password());

        Authentication postAuthentication = authenticationManager.authenticate(preAuthentication);

        if (postAuthentication.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(postAuthentication);
            filterChain.doFilter(requestWrapper, response);
        }
    }

    private boolean isPostMethod(HttpServletRequest request) {
        return "POST".equalsIgnoreCase(request.getMethod());
    }

    private boolean isLoginPath(HttpServletRequest request) {
        return "/api/v1/auth/login".equalsIgnoreCase(request.getServletPath());
    }

    private LoginRequest parseBody(HttpServletRequest request) throws IOException {
        return objectMapper.readValue(request.getInputStream(), LoginRequest.class);
    }
}
