package com.rayyanhunerkar.todolist.util.jwt;

import com.rayyanhunerkar.todolist.exception.JwtException;
import com.rayyanhunerkar.todolist.model.User;
import com.rayyanhunerkar.todolist.service.UserService;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ServletException, IOException {
        if (isPermittedEndpoint(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        final String requestToken = request.getHeader("Authorization");
        String userName;
        String jwtToken;
        if (requestToken != null && requestToken.startsWith("Bearer ")) {
            jwtToken = requestToken.substring(7);
            try {
                userName = jwtTokenUtil.getUserNameFromToken(jwtToken);
            } catch (IllegalArgumentException | ExpiredJwtException e) {
                throw e;
            }
        } else {
            throw new JwtException("Authorization Token not found!");
        }
        if (userName != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            User user = this.userService.loadUserByUsername(userName);
            if (jwtTokenUtil.ValidateJwtToken(jwtToken, user)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                        user, null, user.getAuthorities()
                );
                usernamePasswordAuthenticationToken.setDetails(
                        new WebAuthenticationDetailsSource().buildDetails(request)
                );
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);
    }

    private boolean isPermittedEndpoint(HttpServletRequest request) {
        // Check if the request URL matches any of the permitted endpoints
        String requestURI = request.getRequestURI();
        return requestURI.contains("/auth/") || requestURI.contains("/swagger-ui") || requestURI.contains("/api-docs");
    }
}
