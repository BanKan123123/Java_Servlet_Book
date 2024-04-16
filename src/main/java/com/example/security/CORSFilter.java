package com.example.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

@WebFilter(filterName = "CORSFilter", urlPatterns = {"/*"})
public class CORSFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(CORSFilter.class);
    private final Set<String> allowedOrigins = new HashSet<>();

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("CORSFilter initialized!");

        allowedOrigins.add("http://localhost:3000");
        allowedOrigins.add("http://localhost:4200");
        allowedOrigins.add("http://localhost:8080");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
        doBeforeProcessing(servletRequest, servletResponse);
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } catch (Throwable t) {
            LOGGER.error(t);
        }
    }

    private void doBeforeProcessing(ServletRequest req, ServletResponse res) {
        if (res instanceof HttpServletResponse) {
            HttpServletResponse alteredResponse = ((HttpServletResponse) res);

            if (req instanceof HttpServletRequest) {
                HttpServletRequest alteredRequest = (HttpServletRequest) req;
                String origin = alteredRequest.getHeader("Origin");
                if (isValidDomain(origin)) {
                    alteredResponse.setHeader("Access-Control-Allow-Origin", origin);
                } else {
                    alteredResponse.setHeader("Access-Control-Allow-Origin", "");
                }
            } else {
                alteredResponse.setHeader("Access-Control-Allow-Origin", "*");
            }

            alteredResponse.setHeader("Access-Control-Allow-Methods", "POST, GET, OPTIONS, PUT, DELETE, HEAD");
            alteredResponse.setHeader("Access-Control-Allow-Headers", "X-PINGOTHER, Origin, X-Requested-With, Content-Type, Accept, Content-Encoding");
            alteredResponse.setHeader("Access-Control-Max-Age", "86400");
            alteredResponse.setHeader("Access-Control-Allow-Credenticals", "true");
        }

    }

    private boolean isValidDomain(String origin) {
        if (origin == null) {
            return false;
        }
        for (String allowedOrigin : allowedOrigins) {
            if (origin.contains(allowedOrigin)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void destroy() {
        LOGGER.info("CORSFilter destroyed!");
    }
}
