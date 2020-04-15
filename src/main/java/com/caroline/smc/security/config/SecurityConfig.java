package com.caroline.smc.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.caroline.smc.security.exception.JWTAccessDeniedHandler;
import com.caroline.smc.security.exception.JWTAuthenticationEntryPoint;
import com.caroline.smc.security.filter.JWTAuthenticationFilter;
import com.caroline.smc.security.filter.JWTAuthorizationFilter;
import com.caroline.smc.security.service.UserDetailsServiceImpl;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsServiceImpl userDetailsServiceImpl;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService createUserDetailsService() {
		return userDetailsServiceImpl;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		// to use custom user service and password encoder setting
		auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(bCryptPasswordEncoder());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
				// disable CSRF
				.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/auth/login").permitAll()
				.antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/resources/**").permitAll()
                .antMatchers("/webjars/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/v2/**").permitAll()
				// to access needs authenticated
				.antMatchers("/api/**").authenticated().antMatchers(HttpMethod.DELETE, "/api/**").hasRole("ADMIN")
				// to permit others
				.anyRequest().permitAll().and()
				// to use JWT authentication/authorization filter
				.addFilter(new JWTAuthenticationFilter(authenticationManager()))
				.addFilter(new JWTAuthorizationFilter(authenticationManager()))
				// to remove session
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
				// to use custom JWT authentication handler and entry point
				.exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
				.accessDeniedHandler(new JWTAccessDeniedHandler());
		// to prevent the invading of frame from H2 web
		http.headers().frameOptions().disable();
//		http.headers().cacheControl();
	}

}
