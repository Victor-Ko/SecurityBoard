package com.victor.securityboard.member.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.victor.securityboard.member.domain.AuthorityVO;

@Repository
public class AuthorityDaoImpl implements AuthorityDao{

	@Autowired
	SqlSession session;
	
	@Override
	public List<AuthorityVO> selectAuth(String id) {
		return session.selectList("MemberDao.selectAuth", id);
	}

	@Override
	public void insertAuth(AuthorityVO authorityVO) {
		session.insert("MemberDao.insertAuth", authorityVO);
	}

}
