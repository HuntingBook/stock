package com.caroline.smc.security.util;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.crypto.SecretKey;
import javax.xml.bind.DatatypeConverter;

import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.caroline.smc.security.constants.SecurityConstants;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class JwtTokenUtils {

	/**
	 * setting API secret key
	 */
	private static byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(SecurityConstants.JWT_SECRET_KEY);
	private static SecretKey secretKey = Keys.hmacShaKeyFor(apiKeySecretBytes);

	public static String createToken(String username, List<String> roles, boolean isRememberMe) {
		long expiration = isRememberMe ? SecurityConstants.EXPIRATION_REMEMBER : SecurityConstants.EXPIRATION;

		String tokenPrefix = Jwts.builder().setHeaderParam("typ", SecurityConstants.TOKEN_TYPE)
				.signWith(secretKey, SignatureAlgorithm.HS256)
				.claim(SecurityConstants.ROLE_CLAIMS, String.join(",", roles)).setIssuer("Caroline SMC")
				.setIssuedAt(new Date()).setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + expiration * 1000)).compact();
		return SecurityConstants.TOKEN_PREFIX + tokenPrefix;
	}

//	private boolean isTokenExpired(String token) {
//		Date expiredDate = getTokenBody(token).getExpiration();
//		return expiredDate.before(new Date());
//	}

	public static String getUsernameByToken(String token) {
		return getTokenBody(token).getSubject();
	}

	/**
	 * get all roles of user
	 */
	public static List<SimpleGrantedAuthority> getUserRolesByToken(String token) {
		String role = (String) getTokenBody(token).get(SecurityConstants.ROLE_CLAIMS);
		return Arrays.stream(role.split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
	}

	private static Claims getTokenBody(String token) {
		return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token).getBody();
	}
}
