package com.victor.securityboard.member.dao;

import com.victor.securityboard.member.domain.MemberVO;

public interface MemberDao {

	//로그인 시 회원정보 검색
	public MemberVO selectMember(String id);
	
	//회원가입
	public void insertMember(MemberVO memberVO);
	
	//회원 수정
	public void updateMember(MemberVO memberVO);
	
	//회원 탈퇴
	public void deleteMember(MemberVO memberVO);
	
	//권한 삭제
	public void deleteAuth(MemberVO memberVO);
}
