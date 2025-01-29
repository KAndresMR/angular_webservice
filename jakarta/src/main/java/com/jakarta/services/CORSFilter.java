package com.jakarta.services;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.annotation.WebFilter;

@WebFilter("/*")
public class CORSFilter implements Filter{

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(jakarta.servlet.ServletRequest servletRequest, jakarta.servlet.ServletResponse servletResponse,
            FilterChain filterChain) throws IOException, ServletException {

        @SuppressWarnings("unused")
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        // Agrega encabezados CORS a la respuesta
        response.setHeader("Access-Control-Allow-Origin", "*"); // Permite el acceso desde cualquier origen
        response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD"); // Métodos permitidos
        response.setHeader("Access-Control-Allow-Headers", "Origin, Content-Type, Accept, Authorization"); // Encabezados permitidos
        response.setHeader("Access-Control-Allow-Credentials", "true"); // Permite credenciales en solicitudes CORS
        response.setHeader("Access-Control-Max-Age", "3600"); // Tiempo en segundos para almacenar en caché la respuesta CORS

        // Pasa la solicitud y respuesta a la siguiente etapa del filtro
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
    }

    
}
