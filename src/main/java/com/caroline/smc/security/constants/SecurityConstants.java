package com.caroline.smc.security.constants;

public class SecurityConstants {

	/**
	 * login URL
	 */
	public static final String AUTH_LOGIN_URL = "/auth/login";

	/**
	 * key of role
	 **/
	public static final String ROLE_CLAIMS = "rol";

	/**
	 * rememberMe to be false, the expiration will be an hour
	 */
	public static final long EXPIRATION = 60L * 60L;

	/**
	 * rememberMe to be true, the expiration will be 7 days
	 */
	public static final long EXPIRATION_REMEMBER = 60 * 60 * 24 * 7L;

	/**
	 * JWT's private signed key
	 */
	public static final String JWT_SECRET_KEY = "C*F-JaNdRgUkXn2r5u8x/A?D(G+KbPeShVmYq3s6v9y$B&E)H@McQfTjWnZr4u7w";

	/**
	 * JWT token defaults setting
	 */
	public static final String TOKEN_HEADER = "Authorization";
	public static final String TOKEN_PREFIX = "Bearer ";
	public static final String TOKEN_TYPE = "JWT";

	private SecurityConstants() {
	}
}
