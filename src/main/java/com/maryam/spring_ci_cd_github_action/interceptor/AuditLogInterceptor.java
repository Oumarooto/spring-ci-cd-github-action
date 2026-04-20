package com.maryam.spring_ci_cd_github_action.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

// Cette classe intercepte les requêtes pour produire des logs structurés.

@Component
@Slf4j
public class AuditLogInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        request.setAttribute("executionStartTime", System.currentTimeMillis());
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        long startTime = (long) request.getAttribute("executionStartTime");
        long duration = System.currentTimeMillis() - startTime;

        // Message structuré pour le parsing Logstash
        log.info("TYPE=AUDIT | METHOD={} | URI={} | STATUS={} | DURATION={}ms | REMOTE_IP={}",
                request.getMethod(),
                request.getRequestURI(),
                response.getStatus(),
                duration,
                request.getRemoteAddr());
    }
}
