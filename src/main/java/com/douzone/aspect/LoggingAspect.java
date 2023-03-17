package com.douzone.aspect;

import java.util.Enumeration;
import java.util.LinkedHashMap;
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

	@Pointcut("within(*..*Controller)")
	public void loggerPointCut() {
	}

	@Around("loggerPointCut()")
	public Object methodLogger(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
		try {
			Object result = proceedingJoinPoint.proceed();
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
					.getRequest();
			String layerName = proceedingJoinPoint.getSignature().getDeclaringType().getSimpleName();
			String methodName = proceedingJoinPoint.getSignature().getName();

			Map<String, Object> params = new LinkedHashMap<>();

			try {
				params.put("Controller", color(PURPLE, layerName));
				params.put("http_method", color(GREEN, request.getMethod()));
				params.put("method", color(RED, methodName));
				params.put("request_uri", color(YELLOW, request.getRequestURI()));

			} catch (Exception e) {
				log.error("LoggerAspect error", e);
			}
			log.info("\nrequest: {}", params);
			log.info("\nparams: {{}", color(CYAN, getParams(request)) + "}");
			return result;

		} catch (Throwable throwable) {
			throw throwable;
		}
	}

	private static String getParams(HttpServletRequest request) {
		StringBuilder result = new StringBuilder();
		Enumeration<String> params = request.getParameterNames();
		while (params.hasMoreElements()) {
			String param = params.nextElement();
			String replaceParam = param.replaceAll("\\.", "-");
			String value = request.getParameter(param);
			result.append(replaceParam).append(" : ").append(value);
			if (params.hasMoreElements()) {
				result.append(",\n\t ");
			}
		}
		return result.toString();
	}

	private static String color(String colorCode, String text) {
		return colorCode + text + RESET;
	}
}
