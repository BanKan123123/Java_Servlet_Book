package com.example.security;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpFilter;

import java.io.IOException;

//@WebFilter(filterName = "SecurityFilter", urlPatterns = {"/*"})
public class SecurityFilter extends HttpFilter {

    private static final String SECRET = System.getProperty("SECRET_KEY");

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

    }

}
