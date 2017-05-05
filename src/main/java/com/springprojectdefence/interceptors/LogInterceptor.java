package com.springprojectdefence.interceptors;

import com.springprojectdefence.models.bindingModels.dto.LogDto;
import com.springprojectdefence.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LogInterceptor implements HandlerInterceptor {

    private final LogService logService;

    @Autowired
    public LogInterceptor(LogService logService) {
        this.logService = logService;
    }

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
        httpServletRequest.setAttribute("preHandleTime", System.currentTimeMillis());

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        httpServletRequest.setAttribute("postHandleTime", System.currentTimeMillis());
    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler, Exception e) throws Exception {

        long currentTime = System.currentTimeMillis();
        long preHandleTime = (long) httpServletRequest.getAttribute("preHandleTime");
        long postHandleTime = (long) httpServletRequest.getAttribute("postHandleTime");

        long actionExecution = postHandleTime - preHandleTime;
        long overallExecution = currentTime - preHandleTime;

        HandlerMethod handlerMethod = (HandlerMethod) handler;

        String message = String.format("[%s - %s] Action Execute Time: %d ms, Overall Execute Time %d ms",
                handlerMethod.getBean().getClass(), handlerMethod.getMethod().getName(), actionExecution, overallExecution);

        LogDto logDto = new LogDto(message);

        this.logService.create(logDto);
    }
}
