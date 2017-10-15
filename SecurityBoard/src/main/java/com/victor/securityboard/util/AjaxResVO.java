package com.victor.securityboard.util;

public class AjaxResVO {

	public String result; // Y, N
	public String message; //응답 메세지
	public String redirectUrl; // 처리완료후 이동할 url
	
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getRedirectUrl() {
		return redirectUrl;
	}
	public void setRedirectUrl(String redirectUrl) {
		this.redirectUrl = redirectUrl;
	}
	
}
