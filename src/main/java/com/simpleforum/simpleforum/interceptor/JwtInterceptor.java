package com.simpleforum.simpleforum.interceptor;

import com.auth0.jwt.exceptions.AlgorithmMismatchException;
import com.auth0.jwt.exceptions.SignatureVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.simpleforum.simpleforum.util.JwtUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtInterceptor implements HandlerInterceptor {
    final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            return true;
        }

        Map<String, Object> map = new HashMap<>();
        String token = request.getHeader("token");
        try {
            JwtUtils.verify(token);
            return true;
        } catch (SignatureVerificationException e) {
            map.put("msg", "Invalid signature");
            map.put("code", 401);
        } catch (TokenExpiredException e) {
            map.put("msg", "Token expired");
            map.put("code", 401);
        } catch (AlgorithmMismatchException e) {
            map.put("msg", "Algorithm mismatch");
            map.put("code", 401);
        } catch (Exception e) {
            map.put("msg", "Invalid token");
            map.put("code", 401);
        }
        map.put("state", false);
        logger.debug("JwtInterceptor: " + map);

        Map<String, Object> res = new HashMap<>();
        res.put("data", map);
        String json = new ObjectMapper().writeValueAsString(res);

        response.setContentType("application/json; charset=UTF-8");
        response.getWriter().print(json);

        return false;
    }
}
