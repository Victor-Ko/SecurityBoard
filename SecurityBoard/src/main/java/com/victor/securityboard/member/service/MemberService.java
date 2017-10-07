package com.victor.securityboard.member.service;

import com.victor.securityboard.member.domain.MemberVO;

public interface MemberService {

	//로그인 시 회원정보 검색
	public MemberVO selectMember(String id);
	
	//회원가입
	public void insertMember(MemberVO memberVO);
}
