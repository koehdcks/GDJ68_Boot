package com.winter.app.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import com.winter.app.board.PostVO;
import com.winter.app.member.MemberVO;

import lombok.extern.slf4j.Slf4j;
import reactor.core.publisher.Mono;

@Slf4j
public class SecurityLogoutAdd implements LogoutHandler {
	
	@Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
	private String adminKey;
	
	@Override
	public void logout(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
		log.info("================ Logout Add ================");
		
//		this.logoutKakao(response);
	
		
	}
	
	//web-client
	private void userWebClient() {
		
		WebClient webClient = WebClient.create();
		Mono<ResponseEntity<PostVO>> res = webClient.get()
				.uri("https://jsonplaceholder.typicode.com/posts/1")
				.retrieve()
				.toEntity(PostVO.class);
		
		PostVO postVO= res.block().getBody();
		
		log.info("+++++ WebClient:{}",postVO);
		
	}
	
	
	
	//카카오 연결끊기(탈퇴)
	
	
	
	//카카오계정과 함께 로그아웃
	private void logoutKakao(HttpServletResponse response) {
		RestTemplate restTemplate = new RestTemplate();
		StringBuffer sb = new StringBuffer();
		sb.append("https://kauth.kakao.com/oauth/logout?");
		sb.append("client_id=e23f4a482777c58fdb2ac94475a7246d&");
		sb.append("logout_redirect_uri=http://localhost:82/member/kakaoLogout");
		
		try {
			response.sendRedirect(sb.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//카카오함께 로그아웃은 이 방법으로 잘 안돼서 위에 매개변수로 리스판스받아서 샌드리다이렉트
//		ResponseEntity<String> res = restTemplate.getForEntity(""+sb,String.class);
		
//		log.info("++++++++++++++++++++++카카오함께로그아웃:{}+++++++++++++",res);
		
	}
	
	
	private void logoutForKakao(Authentication authentication) {
		RestTemplate restTemplate = new RestTemplate();
		MemberVO memberVO = (MemberVO)authentication.getPrincipal();
		log.info("========== AccessToken:{} ===============",memberVO.getAccessToken());
		
		//헤더 (엑세스토큰 방식)
//		HttpHeaders headers = new HttpHeaders();
//		headers.add("Authorization", "Bearer "+memberVO.getAccessToken());
//		headers.add("Content-Type", "application/x-www-form-urlencoded");
		//(서비스 앱 어드민 키 방식)
		HttpHeaders headers = new HttpHeaders();
		headers.add("Authorization", "KakaoAK 78501c6b766651a446da97ed1aef383e");
		headers.add("Content-Type", "application/x-www-form-urlencoded");
		//파라미터
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("target_id_type","user_id");
		params.add("target_id",memberVO.getName());
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params,headers);
		ResponseEntity<String> res = restTemplate.postForEntity("https://kapi.kakao.com/v1/user/logout", req, String.class);
		
	}
	
	private void userRestTemplate() {
		RestTemplate restTemplate = new RestTemplate();
		
		
		//파라미터 (post방식)
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("postId","1");
		
		
		HttpEntity<MultiValueMap<String, String>> req = new HttpEntity<>(params,null);
		
		//결과가 하나 일 때
//		ResponseEntity<PostVO> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/posts/1",PostVO.class ,req);
		
//		PostVO result = res.getBody();
		
		
		//결과가 여러개 일 때
//		List<PostVO> list = restTemplate.getForObject("https://jsonplaceholder.typicode.com/posts",List.class ,req);
		
		//get방식
		ResponseEntity<String> res = restTemplate.getForEntity("https://jsonplaceholder.typicode.com/comments?postId=1",String.class ,req);
		
		
		log.info("*********** PostVO : {} **************",res);
		
	}
	
}
