package com.pyeonhaeng.api.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;
import java.util.stream.Collectors;

public class RequestLoggingInterceptor implements HandlerInterceptor {
    private static final Logger logger = LoggerFactory.getLogger(RequestLoggingInterceptor.class);


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){

        Map<String, String[]> paramMap = request.getParameterMap();

        String params = paramMap.entrySet().stream().map(entry -> entry.getKey() + ": " + String.join(", ",entry.getValue())).collect(Collectors.joining(" & "));


        logger.info("[preHandle] Remote Address : {} | Method : {} | Requset URI : {} | Requset Parameters : {}",request.getRemoteAddr(),request.getMethod(),request.getRequestURI(), params);
        return true;
    }


    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView){
        logger.info("[postHandler] Response status : {}", response.getStatus());
    }

}
