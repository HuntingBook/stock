package com.caroline.smc.security.filter;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.caroline.smc.security.constants.SecurityConstants;
import com.caroline.smc.security.entity.JwtUser;
import com.caroline.smc.security.util.JwtTokenUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * user name and password passed should create a new JWT Token and return it
 * inside header. e.g. token: "Bearer + token value"
 */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

	private ThreadLocal<Boolean> rememberMe = new ThreadLocal<>();
	private AuthenticationManager authenticationManager;

	public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;

		// to invoke login for detecting whether needs authentication
		super.setFilterProcessesUrl(SecurityConstants.AUTH_LOGIN_URL);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {

		ObjectMapper objectMapper = new ObjectMapper();
		try {
			// to fetch login user information from request input stream
			JwtUser loginUser = objectMapper.readValue(request.getInputStream(), JwtUser.class);
//			rememberMe.set(loginUser.getRememberMe());
			rememberMe.set(false);

			// resetting the user name and password
			UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
					loginUser.getUsername(), loginUser.getPassword());
			return authenticationManager.authenticate(authRequest);
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/**
	 * successful authentication and to generate new token
	 */
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authentication) {

		JwtUser jwtUser = (JwtUser) authentication.getPrincipal();
		List<String> roles = jwtUser.getAuthorities().stream().map(GrantedAuthority::getAuthority)
				.collect(Collectors.toList());

		// create token
		String token = JwtTokenUtils.createToken(jwtUser.getUsername(), roles, rememberMe.get());

		// set token to HTTP Response Header
		response.setHeader(SecurityConstants.TOKEN_HEADER, token);
	}

	@Override
	protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authenticationException) throws IOException {
		response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authenticationException.getMessage());
	}
}
