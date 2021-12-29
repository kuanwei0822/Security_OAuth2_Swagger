package com.example.demo.pass.token;

import org.springframework.stereotype.Component;


// 這個 Class 是用存放第三方登入後自己產生的 Token

@Component
public class Oauth2Token {
	
	private static String oauth2Token;

	public static String getOauth2Token() {
		return oauth2Token;
	}

	public static void setOauth2Token(String oauth2Token) {
		Oauth2Token.oauth2Token = oauth2Token;
	}
	
}
