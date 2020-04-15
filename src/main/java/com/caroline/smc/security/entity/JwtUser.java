package com.caroline.smc.security.entity;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.caroline.smc.entity.User;

public class JwtUser implements UserDetails {
	private static final long serialVersionUID = -7632705460629105387L;
	private Long id;
	private String username;
	private String password;
	private Collection<? extends GrantedAuthority> authorities;
	private Boolean rememberMe;

	public JwtUser() {
	}

	public JwtUser(User user) {
		id = user.getId();
		username = user.getName();
		password = user.getPassword();
		authorities = getRoles(user);
	}

	private List<SimpleGrantedAuthority> getRoles(User user) {
		List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//		Arrays.stream(user.getRoles().split(",")).forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
		user.getRoles().stream().forEach(role -> authorities.add(new SimpleGrantedAuthority("ROLE_" + role)));
//		return getRoles().stream().map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return authorities;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return password;
	}

	@Override
	public String getUsername() {
		return username;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}

	@Override
	public String toString() {
		return "JwtUser{" + "id=" + id + ", username='" + username + '\'' + ", password='" + password + '\''
				+ ", authorities=" + authorities + ", rememberMe=" + rememberMe + '}';
	}

}
