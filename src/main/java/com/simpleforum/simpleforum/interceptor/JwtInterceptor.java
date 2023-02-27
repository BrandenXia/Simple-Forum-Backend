package com.simpleforum.simpleforum.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpleforum.simpleforum.util.JwtUtils;
import com.simpleforum.simpleforum.util.ResponseUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }

        String token = request.getHeader("token");
        ResponseUtils.Response content = ResponseUtils.createResponse();
        try {
            JwtUtils.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            content.error(401, "Invalid signature");
        } catch (TokenExpiredException e) {
            content.error(401, "Token expired");
        } catch (AlgorithmMismatchException e) {
            content.error(401, "Algorithm mismatch");
        } catch (Exception e) {
            content.error(401, "Invalid token");
        }
        logger.debug("JwtInterceptor: " + content);

        String json = new ObjectMapper().writeValueAsString(content);

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(json);

        return false;
    }
}
