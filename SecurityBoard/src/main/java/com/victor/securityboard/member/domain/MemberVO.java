package com.victor.securityboard.member.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MemberVO implements Serializable{

	private static final long serialVersionUID = -8973667729836869508L;
	
	private String id;
	private String pw;
	private String name;
	
	private List<AuthorityVO> authorities = new ArrayList<>();
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<AuthorityVO> getAuthorities() {
		return authorities;
	}
	public void setAuthorities(List<AuthorityVO> authorities) {
		this.authorities = authorities;
	}
	
	@Override
	public String toString() {
		return "MemberVO [id=" + id + ", pw=" + pw + ", name=" + name + ", authorities=" + authorities + "]";
	}
}
