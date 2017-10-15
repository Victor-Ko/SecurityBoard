package com.victor.securityboard.member.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.securityboard.member.dao.MemberDao;
import com.victor.securityboard.member.domain.MemberVO;

@Transactional
@Service
public class MemberServiceImpl implements MemberService{

	@Autowired
	MemberDao memberDao;
	
	@Override
	public MemberVO selectMember(String id) {
		return memberDao.selectMember(id);
	}

	@Override
	public void insertMember(MemberVO memberVO) {
		memberDao.insertMember(memberVO);
	}

	@Override
	public void updateMember(MemberVO memberVO) {
		memberDao.updateMember(memberVO);
	}

	@Override
	public void deleteMember(MemberVO memberVO) {
		memberDao.deleteMember(memberVO);
	}

	@Override
	public void deleteAuth(MemberVO memberVO) {
		memberDao.deleteAuth(memberVO);
	}

}
