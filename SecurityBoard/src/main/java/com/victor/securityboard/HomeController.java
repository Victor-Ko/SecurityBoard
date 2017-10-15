package com.victor.securityboard;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.victor.securityboard.member.domain.MemberVO;
import com.victor.securityboard.security.SecurityUtil;

@Controller
public class HomeController {

	@Autowired
	SecurityUtil util;
	
	@RequestMapping(value="/")
	public String index(){
		return "index";
	}
	
	@RequestMapping(value="/home")
	public void home(HttpServletResponse res) throws IOException{
		
		MemberVO member = null;
		
		member = util.getCurrentMember();
		System.out.println("member : " + member);
		
		if(member == null){
			SecurityContextHolder.clearContext();
			res.sendRedirect("/member/login");
		}
		
		SecurityContextHolder.getContext().
		setAuthentication(new UsernamePasswordAuthenticationToken(member, null, member.getAuthorities()));
		
		/*mav.addObject("member", member);
		mav.setViewName("/board/list");*/
		
		res.sendRedirect("/board/list");
		
	}
}
