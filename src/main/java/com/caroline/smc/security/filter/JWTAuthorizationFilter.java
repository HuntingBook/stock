package com.caroline.smc.security.filter;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.util.StringUtils;

import com.caroline.smc.security.constants.SecurityConstants;
import com.caroline.smc.security.util.JwtTokenUtils;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.security.SignatureException;

public class JWTAuthorizationFilter extends BasicAuthenticationFilter {

	private static final Logger logger = Logger.getLogger(JWTAuthorizationFilter.class.getName());

	public JWTAuthorizationFilter(AuthenticationManager authenticationManager) {
		super(authenticationManager);
	}

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String authorization = request.getHeader(SecurityConstants.TOKEN_HEADER);

		if (authorization == null || !authorization.startsWith(SecurityConstants.TOKEN_PREFIX)) {
			chain.doFilter(request, response);
			return;
		}

		SecurityContextHolder.getContext().setAuthentication(getAuthentication(authorization));
		super.doFilterInternal(request, response, chain);
	}

	/**
	 * get authentication token
	 */
	private UsernamePasswordAuthenticationToken getAuthentication(String authorization) {
		String token = authorization.replace(SecurityConstants.TOKEN_PREFIX, "");
		try {
			String username = JwtTokenUtils.getUsernameByToken(token);
			logger.info("checking username:" + username);

			// get user roles
			List<SimpleGrantedAuthority> userRolesByToken = JwtTokenUtils.getUserRolesByToken(token);
			if (!StringUtils.isEmpty(username)) {
				return new UsernamePasswordAuthenticationToken(username, null, userRolesByToken);
			}

		} catch (SignatureException | ExpiredJwtException | MalformedJwtException
				| IllegalArgumentException exception) {

			logger.warning("Request to parse JWT with invalid signature . Detail : " + exception.getMessage());
		}
		return null;
	}
}
