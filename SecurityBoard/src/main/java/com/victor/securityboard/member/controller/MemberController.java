package com.victor.securityboard.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.victor.securityboard.member.domain.MemberVO;
import com.victor.securityboard.member.service.MemberService;
import com.victor.securityboard.security.SecurityUtil;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	SecurityUtil util;

	/*@Autowired
	HttpSession session;*/
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	//로그인
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
		return "login";
	}
	
	@RequestMapping(value="/loginAction")
	public ModelAndView loginAction(HttpServletResponse res, MemberVO memberVo) throws IOException{
		ModelAndView mav = new ModelAndView();
		
		MemberVO member = null;
		
		member = util.getCurrentMember();
		System.out.println("member : " + member);
		
		if(member == null){
			SecurityContextHolder.clearContext();
			res.sendRedirect("/login");
		}
		
		SecurityContextHolder.getContext().
		setAuthentication(new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities()));
		
		mav.addObject("member", member);
		mav.setViewName("home");
		
		return mav;
	}
	
	//로그아웃
	@RequestMapping(value="/logout")
	public void logout(HttpServletResponse res, HttpSession session) throws IOException{
		session.invalidate();
		
		res.sendRedirect("/");
	}
	
	//회원가입
	@RequestMapping(value="/join", method=RequestMethod.GET)
	public String join(){
		return "/member/joinForm";
	}
	
	@RequestMapping(value="/joinAction")
	public void joinAction(HttpServletResponse res, MemberVO memberVO) throws IOException{
		
		System.out.println("아이디 : " + memberVO.getId());
		System.out.println("비밀번호 : " + memberVO.getPw());
		System.out.println("이름 : " + memberVO.getName());
		
		System.out.println(pwEncoder.encode(memberVO.getPw()));
		
		memberVO.setPw(pwEncoder.encode(memberVO.getPw()));
		
		if(memberVO != null){
			//memberService.insertMember(memberVO);
		}
		
		res.sendRedirect("/");
	}
	
	//회원수정
	
	//회원탈퇴
}
