package com.victor.securityboard.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.victor.securityboard.member.domain.AuthorityVO;
import com.victor.securityboard.member.domain.MemberVO;
import com.victor.securityboard.member.service.AuthorityService;
import com.victor.securityboard.member.service.MemberService;
import com.victor.securityboard.security.SecurityUtil;
import com.victor.securityboard.util.AjaxResVO;

@Controller
@RequestMapping(value="/member")
public class MemberController {

	@Autowired
	MemberService memberService;
	
	@Autowired
	AuthorityService authorityService;
	
	@Autowired
	SecurityUtil util;
	
	/*@Autowired
	HttpSession session;*/
	
	@Autowired
	PasswordEncoder pwEncoder;
	
	//로그인
	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String login(){
		return "member/login";
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
		
		/*System.out.println("아이디 : " + memberVO.getId());
		System.out.println("비밀번호 : " + memberVO.getPw());
		System.out.println("이름 : " + memberVO.getName());
		
		System.out.println(pwEncoder.encode(memberVO.getPw()));*/
		
		memberVO.setPw(pwEncoder.encode(memberVO.getPw()));
		
		if(memberVO != null){
			System.out.println(memberVO.toString());
			memberService.insertMember(memberVO);
			
			AuthorityVO authorityVO = new AuthorityVO();
			
			authorityVO.setId(memberVO.getId());
			authorityVO.setAuth("ROLE_USER");
			
			authorityService.insertAuth(authorityVO);
		}
		
		res.sendRedirect("/");
	}
	
	//마이페이지
	@RequestMapping(value="/mypage")
	public ModelAndView mypage(HttpSession session){
		
		ModelAndView mav = new ModelAndView();
		
		MemberVO member = null;
		
		member = util.getCurrentMember();
		
		member.setPw(member.getPw());
		
		mav.addObject("member", member);
		mav.setViewName("/member/mypage");
		
		return mav;
	}
	
	//회원수정 폼
	@RequestMapping(value="/update")
	public ModelAndView updateForm(HttpSession session){
		
		ModelAndView mav = new ModelAndView();
		MemberVO member = new MemberVO();
		
		member = util.getCurrentMember();
		
		mav.addObject("member", member);
		mav.setViewName("/member/updateForm");
		
		return mav;
	}
	
	@RequestMapping(value="/updateAction")
	@ResponseBody
	public AjaxResVO updateAction(@RequestParam("id")String id, @RequestParam("pw")String pw){
		
		MemberVO memberVo = new MemberVO();
		MemberVO member = new MemberVO();
		AjaxResVO ajaxResVO = new AjaxResVO();
		
		member = util.getCurrentMember();
		
		if(member != null){
			memberVo.setId(id);
			memberVo.setPw(pwEncoder.encode(pw));
			
			System.out.println(memberVo);
			
			try{
				memberService.updateMember(memberVo);
				ajaxResVO.setResult("Y");
				ajaxResVO.setMessage("회원수정 성공");
				ajaxResVO.setRedirectUrl("/member/mypage");
			}catch(Exception e){
				e.printStackTrace();
				ajaxResVO.setResult("N");
				ajaxResVO.setMessage("회원수정 실패");
				ajaxResVO.setRedirectUrl("/member/mypage");
			}
		}else{
			ajaxResVO.setResult("N");
			ajaxResVO.setMessage("올바른 접근이 아닙니다");
			ajaxResVO.setRedirectUrl("/member/login");
		}
		
		return ajaxResVO;
	}
	
	//회원탈퇴
	@RequestMapping(value="/deleteAction")
	public void deleteAction(HttpServletResponse res, HttpSession session) throws IOException{
	
		String id = (String)session.getAttribute("id");
		
		memberService.deleteMember(id);
		
		session.invalidate();
		
		res.sendRedirect("/");
	}
	
	@ExceptionHandler(Exception.class)
	private void exceptionHandler(HttpServletRequest req, Exception e) {
		System.err.println(e.getMessage());
	}
	
	@ExceptionHandler(AccessDeniedException.class)
	private void AccessDeniedExceptionHandler(HttpServletRequest req, HttpServletResponse res, Exception e) throws IOException {
		System.err.println(e.getMessage());
		res.sendRedirect("/");
	}
}
