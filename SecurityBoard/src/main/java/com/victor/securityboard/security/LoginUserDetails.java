package com.victor.securityboard.security;

import org.springframework.security.core.userdetails.User;

import com.victor.securityboard.member.domain.MemberVO;

public class LoginUserDetails extends User {
	
	private static final long serialVersionUID = -3541063887975984911L;
	
	private MemberVO member = null;
	
	public LoginUserDetails(MemberVO member) {
		
		super(member.getId(), member.getPw(), true, true, true, true, member.getAuthorities());
		this.member = member;
		
		//System.out.println("LoginUserDetail member : " + member);
		//String id = member.getId();
	}

	public MemberVO getMember() {
		return member;
	}

}
