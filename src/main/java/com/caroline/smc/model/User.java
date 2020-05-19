package com.caroline.smc.model;

import java.util.Arrays;
import java.util.List;

public class User {

	private Long id;
	private String name;
	private String password;
	private String roles;
	private String email;
	private String mobile;
	private boolean confirmed;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public boolean isConfirmed() {
		return confirmed;
	}

	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<String> getRoles() {
//		return roles;
		return Arrays.asList(roles.split(","));
	}

	public void setRoles(List<String> roles) {
		this.roles = String.join(",", roles);
	}
}
