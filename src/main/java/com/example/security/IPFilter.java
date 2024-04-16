package com.example.security;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.IOException;

@WebFilter(filterName = "IPFilter", urlPatterns = {"/*"})
public class IPFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(CORSFilter.class);

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        LOGGER.info("IPFilter initialized!");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        if (servletRequest instanceof HttpServletRequest) {
            HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
            String requestIp = httpServletRequest.getRemoteAddr();
            LOGGER.info(String.format("Requester's IP: %s", requestIp));


            filterChain.doFilter(servletRequest, servletResponse);

        } else {
            LOGGER.error("Received a non HTTP request. Unable to retrieve requester's IP.");
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    @Override
    public void destroy() {
        LOGGER.info("IPFilter destroyed");
    }
}
