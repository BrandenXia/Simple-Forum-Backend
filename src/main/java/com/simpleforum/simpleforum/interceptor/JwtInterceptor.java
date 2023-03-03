package com.simpleforum.simpleforum.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpleforum.simpleforum.dto.ResponseDTO;
import com.simpleforum.simpleforum.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }

        String token = request.getHeader("token");
        ResponseDTO.ResponseDTOBuilder content = ResponseDTO.builder();
        try {
            JwtUtils.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            content.status(401).error("Signature verification failed");
        } catch (TokenExpiredException e) {
            content.status(401).error("Token expired");
        } catch (AlgorithmMismatchException e) {
            content.status(401).error("Algorithm mismatch");
        } catch (Exception e) {
            content.status(401).error("Invalid token");
        }
        logger.debug("JwtInterceptor: " + content);

        String json = new ObjectMapper().writeValueAsString(content.build());

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(json);

        return false;
    }
}
