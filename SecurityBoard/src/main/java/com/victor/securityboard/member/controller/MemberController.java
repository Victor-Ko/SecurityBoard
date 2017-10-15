package com.victor.securityboard.member.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
			//System.out.println(memberVO.toString());
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
		
		/*String id = (String)session.getAttribute("id");*/
		
		MemberVO member = null;
		
		member = util.getCurrentMember();
		
		mav.addObject("member", member);
		mav.setViewName("/member/mypage");
		
		return mav;
	}
	
	//회원수정
	@RequestMapping(value="/update")
	public ModelAndView updateForm(HttpSession session){
		
		String id = (String)session.getAttribute("id");
		
		MemberVO memberVO = memberService.selectMember(id);
		
		ModelAndView mav = new ModelAndView();
		
		mav.addObject("member", memberVO);
		mav.setViewName("/member/updateForm");
		
		return mav;
	}
	
	@RequestMapping(value="/updateAction")
	public ModelAndView updateAction(HttpSession session, MemberVO memberVO){
		ModelAndView mav = new ModelAndView();
		
		memberVO.setPw(pwEncoder.encode(memberVO.getPw()));
		
		if(memberVO != null){
			memberService.updateMember(memberVO);
		}
		
		mav.setViewName("/member/mypage");
		
		return mav;
	}
	
	//회원탈퇴
	@RequestMapping(value="/deleteAction")
	@ResponseBody
	public AjaxResVO deleteAction(HttpServletRequest req, HttpServletResponse res, MemberVO memberVo) throws IOException{
	
		HttpSession session = req.getSession();
		AjaxResVO resVo     = new AjaxResVO();
		
		MemberVO loginUser = util.getCurrentMember();
		
		// 1. 로그인 상태가 아닐시
		// 2. 프론트로부터 사용자 id의 데이터가 넘어오지 않은 경우
		// 3. 현재 사용자가 보고있는 페이지의 데이터가 로그인한 사용자와 정보가 다를 때 
		if(loginUser == null || memberVo.getId() == null || !memberVo.getId().equals(loginUser.getId())) {
			resVo.setResult("N");
			resVo.setMessage("올바른 접근이 아닙니다.");
			resVo.setRedirectUrl("");
		}else {
			// 권한 삭제후 해당 계정 삭제
			memberService.deleteAuth(memberVo);
			memberService.deleteMember(memberVo);
			session.invalidate();
			
			resVo.setResult("Y");
			resVo.setMessage("회원탈퇴가 완료되었습니다.");
			resVo.setRedirectUrl("/");
		}
		
		return resVo;
	}
}
