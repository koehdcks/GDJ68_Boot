package com.winter.app.config;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class SecurityFailHandler implements AuthenticationFailureHandler {@Override
	
	
	
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		log.info("================= Exception : {} ================",exception);
		String message="로그인 실패";
		
		if(exception instanceof InternalAuthenticationServiceException) {
			//message="아이디 틀림";
			message="login.fail.nouser";
		}
		
		if(exception instanceof BadCredentialsException) {
			message="login.fail.notpassword";
		}
		if(exception instanceof AccountExpiredException) {
			/* isAccountNonExpired = false*/
			message="계정 유효기간 만료";
		}
		if(exception instanceof LockedException) {
			/* isAccountNonLocked */
			message="계정 잠김";
		}
		
		message = URLEncoder.encode(message,"UTF-8");
		
		response.sendRedirect("/member/login?message="+message);
		
	}

	
}
