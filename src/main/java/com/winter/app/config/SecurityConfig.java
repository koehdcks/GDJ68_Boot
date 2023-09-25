package com.winter.app.config;

import org.aspectj.weaver.ast.And;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	
	//public 을 선언하면 default로 바꾸라는 메세지 출력
	@Bean
	WebSecurityCustomizer webSecurityConfig() {
		//Security에서 무시해야하는 URL 패턴 등록
		return web -> web
				.ignoring()
				.antMatchers("/css/**")
				.antMatchers("/img/**")	
				.antMatchers("/js/**")
				.antMatchers("/vendor/**");
	}
	
	@Bean
	SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
		httpSecurity
			.cors()
			.and()
			.csrf()
			.disable()
			.authorizeRequests()
				.antMatchers("/notice/add").hasRole("ADMIN")//ROLE_ADMIN에서 ROLE_제외
				.antMatchers("/manager/*").hasAnyRole("ADMIN","MANAGER")
//				.antMatchers("/manager/*").access("hasRole('ROLE_ADMIN')")
				.antMatchers("/").permitAll()
				.and()
		//form 관련 설정
			.formLogin()
				.loginPage("/member/login") //내장된 로그인폼을 사용하지 않고, 개발자가 만든 폼을 사용
				.defaultSuccessUrl("/") //성공했을때 어디로 갈건지
				.failureUrl("/member/login")// 실패했을때
				.permitAll()
				.and()
			.logout()
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/")
				.invalidateHttpSession(true)
				.and()
			.sessionManagement()
			;
			
		
			return httpSecurity.build();
	
	}
	
}
