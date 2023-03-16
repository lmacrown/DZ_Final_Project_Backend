package com.douzone.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

public class LoggingInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(LoggingInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		if (handler instanceof HandlerMethod) {

				HandlerMethod handlerMethod = (HandlerMethod) handler;
				logger.info("Request : {} {}", request.getMethod(), request.getRequestURI());
				logger.info("Method : {}", handlerMethod.getMethod().getName());
				request.setAttribute("startTime", System.currentTimeMillis());
					
		
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		if (handler instanceof HandlerMethod) {
			HandlerMethod handlerMethod = (HandlerMethod) handler;
	
			long startTime = (Long) request.getAttribute("startTime");
			long endTime = System.currentTimeMillis();
			long processingTime = endTime - startTime;

			logger.info("Method : {}", handlerMethod.getMethod().getName());
			logger.info("Response : {} {}", request.getMethod(), request.getRequestURI());
			logger.info("Status : {} - Processing time : {} ms", response.getStatus(), processingTime);

		}

	}
	 @Override
	    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
	        request.removeAttribute("requestWrapper");
	    }

}
