package com.ambientese.grupo5.Filters;

import java.io.IOException;

import org.springframework.lang.NonNull;
import org.springframework.web.filter.OncePerRequestFilter;

import com.ambientese.grupo5.Services.UsuarioService.JWTUtil;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain)
        throws ServletException, IOException {
        String header = request.getHeader("Authorization");
        String requestedWith = request.getHeader("X-Requested-With");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);
            String login = JWTUtil.validateToken(token);
            if (login != null) {
                filterChain.doFilter(request, response);
                return;
            }
        }

        String requestURI = request.getRequestURI();
        if ("InsideApplication".equals(requestedWith) || requestURI.startsWith("/api/")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        } else {
            response.sendRedirect("/ranking");
        }
    }
}
