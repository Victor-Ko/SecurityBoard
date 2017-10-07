package com.victor.securityboard.member.dao;

import com.victor.securityboard.member.domain.MemberVO;

public interface MemberDao {

	public MemberVO selectMember(String id);
}
