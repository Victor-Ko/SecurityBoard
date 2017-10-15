package com.victor.securityboard.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.victor.securityboard.member.domain.MemberVO;
import com.victor.securityboard.member.service.AuthorityService;
import com.victor.securityboard.member.service.MemberService;

@Transactional
@Service
@EnableCaching(proxyTargetClass=true)
public class LoginUserDetaisService implements UserDetailsService {

	@Autowired
	private MemberService memberService;
	
	@Autowired
	AuthorityService authorityService;

	// 해당 콜백 메소드는 스프링 시큐리티에서 로그인 체크를 진행할 때 호출된다.
	@Transactional(readOnly=true)
	@Override
	public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
		MemberVO member = memberService.selectMember(id);
		
		// 멤버 권한 셋팅 필요
		member.setAuthorities(authorityService.selectAuth(member.getId()));
		
		//System.out.println("loadUserByUsername member : " + member);
		return new LoginUserDetails(member);
	}
}
