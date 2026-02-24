package com.giliannereyes.calculatorspring.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * Logs all inbound HTTP requests once per request lifecycle.
 */
@Component
public class RequestLoggingFilter extends OncePerRequestFilter {

  private static final Logger logger = LoggerFactory.getLogger(RequestLoggingFilter.class);

  /**
   * Executes the next filter and logs request metadata after completion.
   *
   * @param request current HTTP request
   * @param response current HTTP response
   * @param filterChain active servlet filter chain
   * @throws ServletException when downstream processing fails with a servlet error
   * @throws IOException when downstream processing fails with an I/O error
   */
  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
      throws ServletException, IOException {
    long startTime = System.currentTimeMillis();

    try {
      filterChain.doFilter(request, response);
    } finally {
      long durationMs = System.currentTimeMillis() - startTime;
      logger.info(
          "Request {} {} from {} -> status={} in {}ms",
          request.getMethod(),
          request.getRequestURI(),
          request.getRemoteAddr(),
          response.getStatus(),
          durationMs
      );
    }
  }
}
