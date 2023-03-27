package com.douzone.interceptor;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class LoginMemberInterceptor extends HandlerInterceptorAdapter {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		System.out.println("인터셉터 실행");
		HttpSession session = request.getSession();
		//로그인 여부 확인(컨트롤러로 확인)
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
//		Date checkActiveDate=(Date) session.getAttribute("check_active");
//		
//		Calendar cal = Calendar.getInstance();
//		cal.add(Calendar.MINUTE, -1);
//		
//		boolean isAfter = cal.getTime().before(checkActiveDate);
//		
//		if(isAfter)...........................................................00.0...................................................................................................................................................................................................................................................................................................................
//			session.invalidate();
//		if(!(Boolean) session.getAttribute("isLogon")) {
//			log.info("멤버 실패");
//			response.sendRedirect("http://localhost:3000/login");
//			return false;
//		}
//		//session.setAttribute("org.springframework.web.servlet.i18n.SessionLocaleResolver.LOCALE", new Locale(locale));
		response.sendRedirect("http://localhost:3000/login");
		log.info("멤버 로그인 성공");
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
			System.out.println("인터셉터 끝난후");
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		System.out.println("인터셉터 완전끝난후");
	}
	

}
