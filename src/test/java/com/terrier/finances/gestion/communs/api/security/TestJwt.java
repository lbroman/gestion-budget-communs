package com.terrier.finances.gestion.communs.api.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Calendar;
import java.util.Date;

import org.junit.jupiter.api.Test;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Tests de token JWT
 * @author vzwingma
 *
 */
public class TestJwt {

	
	private static final String TOKEN_VALUE = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJURVNUX1VTRVIiLCJqdGkiOiIwNjhmNzkzNy0xN2Y4LTQ5YzAtYWUwOC0yZDJkMjhjYTY1YjIiLCJVU0VSSUQiOiJURVNUX1VTRVIifQ.n81j9UI_L8-VgP7S2Ruxz5BFT1ys6GjlJe-GD7X1gJ8";
	
	private static final String INVALID_SIGNATURE = "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJURVNUX1VTRVIiLCJqdGkiOiJURVNUX1VTRVIifQ.wZjHEX-HjRtd5-WVXd_gANGQLzJWyMZ945aXlWkqahKwpGFWai2o4_BOW_ToPGsQQZaTfk2Jr3WU1EoQUmC7XQ";
	
	@Test
	public void testToken(){
		
		Long now = Calendar.getInstance().getTimeInMillis();
		String token = Jwts.builder()
				.setSubject("TEST_USER")
				.setId("TEST_USER")
				.claim(JwtConfigEnum.JWT_CLAIM_HEADER_USERID, "TEST_USER")
				.setIssuedAt(new Date(now))
				.setExpiration(new Date(now + JwtConfigEnum.JWT_EXPIRATION_S * 1000))  // in milliseconds
				.signWith(Keys.hmacShaKeyFor(JwtConfigEnum.JWT_SECRET_KEY.getBytes()))
				.compact();
		assertNotNull(token);
	}
	
	
	@Test
	public void testParseToken(){
		String token = Jwts.builder()
				.setSubject("TEST_USER")
				.setId("068f7937-17f8-49c0-ae08-2d2d28ca65b2")
				.claim(JwtConfigEnum.JWT_CLAIM_HEADER_USERID, "TEST_USER")
				.signWith(Keys.hmacShaKeyFor(JwtConfigEnum.JWT_SECRET_KEY.getBytes()))
				.compact();

		assertNotNull(token);
		assertEquals(TestJwt.TOKEN_VALUE, token);
		
		Claims claims = JwtConfigEnum.getJWTClaims(token);
		assertNotNull(claims);
		assertEquals("068f7937-17f8-49c0-ae08-2d2d28ca65b2", claims.getId());
		assertEquals("TEST_USER", claims.getSubject());
		assertEquals("TEST_USER", claims.get(JwtConfigEnum.JWT_CLAIM_HEADER_USERID));
		
	}
	

	@Test
	public void testParseInvalidToken(){
		assertNotNull(TestJwt.INVALID_SIGNATURE);
		assertNull(JwtConfigEnum.getJWTClaims(TestJwt.INVALID_SIGNATURE));
	}
}
