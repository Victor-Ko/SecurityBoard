package com.victor.securityboard.member.dao;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.victor.securityboard.member.domain.MemberVO;

@Repository
public class MemberDaoImpl implements MemberDao{

	@Autowired
	SqlSession session;
	
	@Override
	public MemberVO selectMember(String id) {
		return session.selectOne("MemberDao.selectMember", id);
	}

}
