package com.example.demo.user;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name="USER_LDGR")
public class User {
	
//	@EmbeddedId
//	private UserKey userKey;
	@Id
	@Column(name = "USER_ID", length = 30, nullable = false)
	private String userId;
	
	@Column(name = "USER_PWD", length = 300, nullable = false)
	private String userPwd;
	
	@Column(name = "LOGIN_TYPE", length = 1, nullable = false)
	private String loginType;
	
	@Column(name = "NICK_NAME", length = 30, nullable = false)
	private String nickName;
	
	@Column(name = "PROFILE_IMAGE", length = 100, nullable = true)
	private String profileImage;
	
	@Column(name = "REFRESH_TOKEN", length = 100, nullable = false)
	private String refreshToken;
	
	@Column(name = "ACCESS_TOKEN", length = 100, nullable = false)
	private String accessToken;

	
	/*
	 * public String getUserCode() { return userCode; }
	 * 
	 * public void setUserCode(String userCode) { this.userCode = userCode; }
	 * 
	 * public String getNickName() { return nickName; }
	 * 
	 * public void setNickName(String nickName) { this.nickName = nickName; }
	 * 
	 * public String getProfileImage() { return profileImage; }
	 * 
	 * public void setProfileImage(String profileImage) { this.profileImage =
	 * profileImage; }
	 * 
	 * public String getRefreshToken() { return refreshToken; }
	 * 
	 * public void setRefreshToken(String refreshToken) { this.refreshToken =
	 * refreshToken; }
	 * 
	 * public String getAccessToken() { return accessToken; }
	 * 
	 * public void setAccessToken(String accessToken) { this.accessToken =
	 * accessToken; }
	 * 
	 * public String getLoginType() { return loginType; }
	 * 
	 * public void setLoginType(String loginType) { this.loginType = loginType; }
	 */
}
