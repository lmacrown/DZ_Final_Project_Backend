package com.douzone.logging;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import lombok.extern.slf4j.Slf4j;
@Component
@Aspect
@Slf4j
public class LoggingAspect {
	
	public static final String RESET = "\u001B[0m";
	public static final String RED = "\u001B[31m";
	public static final String GREEN = "\u001B[32m";
	public static final String YELLOW = "\u001B[33m";
	public static final String BLUE = "\u001B[34m";
	public static final String PURPLE = "\u001B[35m";
	public static final String CYAN = "\u001B[36m";

	
	@Pointcut("within(*..*Service) || within(*..*Controller) || within(*..*Dao)")
	public void loggerPointCut() {
	}

	
	@Around("loggerPointCut()")
	public Object methodLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		try {
			Object result = proceedingJoinPoint.proceed();
			HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest(); 
			String layerName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
			String methodName = proceedingJoinPoint.getSignature().getName();
			
			Map<String, Object> params = new HashMap<>();

			try {
				params.put("\n\t"+"layer",PURPLE + layerName+RESET);
				params.put("\n\t"+"method", RED+methodName+RESET);
				params.put("\n\t"+"params", CYAN+"{"+getParams(request)+"}"+RESET);
				params.put("\n\t"+"request_uri",YELLOW + request.getRequestURI()+RESET);
				params.put("\n\t"+"http_method",GREEN + request.getMethod()+RESET);
			} catch (Exception e) {
				log.error("LoggerAspect error", e);
			}
			log.info("\n"+"result:{}", params); 
			
			
			return result;

		} catch (Throwable throwable) {
			throw throwable;
		}
	}


	private static String getParams(HttpServletRequest request) {
		boolean toggle= true;
		String result="";
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
		    String param = params.nextElement();
		    String replaceParam = param.replaceAll("\\.", "-");
		    result += replaceParam;
		    if (toggle) {
		        result += " : ";
		    } else {
		        result += "\n\t\t";
		    }
		    toggle = !toggle;
		}
		//return jsonObject;
		
		return result;
	}
}






	