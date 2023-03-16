package com.douzone.handler;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.douzone.dao.MemberDAO;

@Component
public class AuthSucessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Autowired
	private MemberDAO memberDAO;
	
	@Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        
		memberDAO.updateMemberLastLogin(authentication.getName());
		System.out.println("authentication ->" + authentication);
		
        setDefaultTargetUrl("/board/list.do");
        
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
