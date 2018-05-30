/*
 * Copyright © 2018 krun, All Rights Reserved.
 * Project: melons
 * File:      JwtToken.java
 * Date:    18-5-30 上午9:23
 * Author: krun
 */

package com.krun.melons.core.jwt;

import com.krun.melons.configuration.properties.MelonsJwtProperties;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Clock;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClock;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

/**
 * Jwt 实体，处理校验
 *
 * @author krun
 * @date 2018/05/30
 */
public class JwtToken {

	private        MelonsJwtProperties configuration;
	private        String              token;
	private        Claims              claims;
	private static Clock               clock = DefaultClock.INSTANCE;

	public static JwtToken fromRequest (HttpServletRequest request, MelonsJwtProperties configuration) {
		String token = request.getHeader(configuration.getHeader());
		return parse(token, configuration);
	}

	public static JwtToken parse(String token, MelonsJwtProperties configuration) {
		if (null == token || "".equals(token) || ! token.startsWith(configuration.getPrefix())) {
			throw new RuntimeException("Token 异常!");
		}
		return new JwtToken(configuration, token.substring(configuration.getPrefix()
		                                                                .length()));
	}

	public static JwtToken empty(MelonsJwtProperties configuration) {
		return new JwtToken(configuration);
	}

	private JwtToken (MelonsJwtProperties configuration, String token) {
		this.configuration = configuration;
		this.token = token;
		this.claims = getAllClaims();
	}

	private JwtToken (MelonsJwtProperties configuration) {
		this.configuration = configuration;
	}

	private Claims getAllClaims () {
		return Jwts.parser()
		           .setSigningKey(configuration.getSecret())
		           .parseClaimsJws(token)
		           .getBody();
	}

	public <T> T getClaimFromToken (Function<Claims, T> claimsResolver) {
		T obj =  claimsResolver.apply(claims);
		if (obj == null) {
			throw new RuntimeException("Token 异常!");
		}
		return obj;
	}

	public Date getExpirationDateFromToken () {
		return getClaimFromToken(Claims::getExpiration);
	}

	public boolean isTokenExpired () {
		return getExpirationDateFromToken().before(clock.now());
	}

	public String getUsername() {
		return getClaimFromToken(Claims::getSubject);
	}

	public String generateToken(String username) {
		Map<String, Object> claims = new HashMap<>(0);
		return configuration.getPrefix() + doGenerateToken(claims, username);
	}

	private Date calculateExpirationDate(Date createdDate) {
		return new Date(createdDate.getTime() + configuration.getExpiration() * 1000);
	}

	private String doGenerateToken(Map<String, Object> claims, String subject) {
		final Date createdDate = clock.now();
		final Date expirationDate = calculateExpirationDate(createdDate);

		this.token = Jwts.builder()
		           .setClaims(claims)
		           .setSubject(subject)
		           .setIssuedAt(createdDate)
		           .setExpiration(expirationDate)
		           .signWith(SignatureAlgorithm.HS512, configuration.getSecret())
		           .compact();
		return this.token;
	}

	public String getToken() {
		return this.token;
	}
}
