package com.winter.app.member;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/member/*")
@Slf4j
public class MemberController {
	@Autowired
	private MemberService memberService;
	
	@GetMapping("info")
	public void getInfo() throws Exception{
		
	}
	
	@GetMapping("kakaoLogout")
	public String kakaoLogout() throws Exception{
		
		log.info("+++++++++++++++++++++카카오로그아웃컨트롤러");
		
		return "redirect:/";
	}
	
	@GetMapping("update")
	public void setUpdate(@AuthenticationPrincipal MemberVO memberVO,Model model) throws Exception{
//		MemberVO memberVO = (MemberVO)principal;
//		memberVO = memberService.getLogin(memberVO);
			
		MemberInfoVO memberInfoVO = new MemberInfoVO();
		memberInfoVO.setName(memberVO.getName());
		memberInfoVO.setBirth(memberVO.getBirth());
		memberInfoVO.setEmail(memberVO.getEmail());
		
		model.addAttribute("memberInfoVO", memberInfoVO);
	}
	
	@PostMapping("update")
	public String setUpdate(@Valid MemberInfoVO memberInfoVO,BindingResult bindingResult) throws Exception{
		
		Object obj = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		MemberVO memberVO = (MemberVO)obj;
		
		memberVO.setEmail("UpdateEmail@naver.com");
		
		return "redirect:/";
		
//		bindingResult.getAllErrors();
//		
//		List<FieldError> errors = bindingResult.getFieldErrors();
//		for(FieldError e:errors) {
//			log.info(e.getField());
//		}
	}
	
	
	
	
	@GetMapping("login")
	public String getLogin(@ModelAttribute MemberVO memberVO) throws Exception{
		SecurityContext context = SecurityContextHolder.getContext();
		
		String check = context.getAuthentication().getPrincipal().toString();
		
		if(!check.equals("anonymousUser")) {
			return "redirect:/";
		}
		return "member/login";
	}

	
	
	
	
//	@GetMapping("join")
//	public void setJoin(Model model) throws Exception{
//		MemberVO memberVO = new MemberVO();
//		model.addAttribute("memberVO", memberVO);
//	}
			                                
	@GetMapping("join")	 //model.addAttribute("memberVO",memberVO) 한것과 같은 코드
	public void setJoin(@ModelAttribute MemberVO memberVO) throws Exception{
									//클래스명의 앞글자를 소문자로 바꾼게 키
		
	}
	
	@PostMapping("join")
	public String setJoin(@Valid MemberVO memberVO,BindingResult bindingResult,MultipartFile photo) throws Exception{
		
		//@Valid는 Controller에서만 작동 가능
		
		
		boolean check = memberService.getMemberError(memberVO, bindingResult);
		
		
		if(bindingResult.hasErrors() || check) {
			return "member/join";
		}
		int result = memberService.setJoin(memberVO);
		
		log.info("Photo : {} --- size : {}",photo.getOriginalFilename(),photo.getSize());
		return "redirect:../";
	}
	
}
