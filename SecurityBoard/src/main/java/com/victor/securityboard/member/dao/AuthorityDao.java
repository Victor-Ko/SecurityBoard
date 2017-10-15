package com.victor.securityboard.member.dao;

import java.util.List;

import com.victor.securityboard.member.domain.AuthorityVO;

public interface AuthorityDao {

	public List<AuthorityVO> selectAuth(String id);
	
	public void insertAuth(AuthorityVO authorityVO);
	
}
