package com.terrier.finances.gestion.communs.api.security;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;

public class TestJwt {

	
	private static final String TOKEN_VALUE = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJURVNUX1VTRVIiLCJqdGkiOiIwNjhmNzkzNy0xN2Y4LTQ5YzAtYWUwOC0yZDJkMjhjYTY1YjIiLCJVU0VSSUQiOiJURVNUX1VTRVIifQ.Bi9A2WrU7nWOtZ0GnCF5BL-v1QKkVbfgVVlonfYYA1mmn_44sUL-RglfM4KTLkpg2HejOlxMKDwWNF5sxWEouA";
	
	private static final String INVALID_SIGNATURE = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJURVNUX1VTRVIiLCJqdGkiOiJURVNUX1VTRVIifQ.wZjHEX-HjRtd5-WVXd_gANGQLzJWyMZ945aXlWkqahKwpGFWai2o4_BOW_ToPGsQQZaTfk2Jr3WU1EoQUmC7XQ";
	
	@Test
	public void testToken(){
		
		Long now = Calendar.getInstance().getTimeInMillis();
		String token = Jwts.builder()
				.setSubject("TEST_USER")
				.setId("TEST_USER")
				.claim(JwtConfig.JWT_CLAIM_USERID_HEADER, "TEST_USER")
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + JwtConfig.JWT_EXPIRATION_S * 1000))  // in milliseconds
				.signWith(SignatureAlgorithm.HS512, JwtConfig.JWT_SECRET_KEY.getBytes())
				.compact();

		assertNotNull(token);
	}
	
	
	@Test
	public void testParseToken(){
		String token = Jwts.builder()
				.setSubject("TEST_USER")
				.setId("068f7937-17f8-49c0-ae08-2d2d28ca65b2")
				.claim(JwtConfig.JWT_CLAIM_USERID_HEADER, "TEST_USER")
				.signWith(SignatureAlgorithm.HS512, JwtConfig.JWT_SECRET_KEY.getBytes())
				.compact();

		assertNotNull(token);
		assertEquals(TestJwt.TOKEN_VALUE, token);
		
		Claims claims = JwtConfig.getJWTClaims(token);
		assertNotNull(claims);
		assertEquals("068f7937-17f8-49c0-ae08-2d2d28ca65b2", claims.getId());
		assertEquals("TEST_USER", claims.getSubject());
		assertEquals("TEST_USER", claims.get(JwtConfig.JWT_CLAIM_USERID_HEADER));
		
	}
	

	@Test(expected=SignatureException.class)
	public void testParseInvalidToken(){
		assertNotNull(TestJwt.INVALID_SIGNATURE);
		JwtConfig.getJWTClaims(TestJwt.INVALID_SIGNATURE);
	}
}
