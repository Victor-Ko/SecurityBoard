package com.victor.securityboard.member.service;

import java.util.List;

import com.victor.securityboard.member.domain.AuthorityVO;

public interface AuthorityService {

	public List<AuthorityVO> selectAuth(String id);
}
