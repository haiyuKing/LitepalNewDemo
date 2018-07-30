package com.why.project.litepalnewdemo.bean;

import org.litepal.annotation.Column;
import org.litepal.crud.LitePalSupport;

/**
 * Created by HaiyuKing
 * Used 登录账户信息bean类
 */

public class LoginUserBean extends LitePalSupport{
	@Column(nullable = false)
	private String userName;//不能为空

	@Column(nullable = false)
	private String passWord;

	@Column(unique = true)
	private String userId;//不可重复

	private String tel;

	public String getPassWord() {
		return passWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTel() {
		return tel;
	}

	public void setTel(String tel) {
		this.tel = tel;
	}
}
