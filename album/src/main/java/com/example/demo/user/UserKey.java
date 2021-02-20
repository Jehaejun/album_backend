package com.example.demo.user;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.springframework.data.annotation.Id;

import lombok.Data;

@Data
@Embeddable
public class UserKey implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "USER_CODE", length = 30, nullable = false)
	private String userCode;
	
	@Id
	@Column(name = "LOGIN_TYPE", length = 1, nullable = false)
	private String loginType;
}
