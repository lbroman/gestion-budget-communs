package com.terrier.finances.gestion.communs.api.security;

import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

/**
 * Configu JWT
 * @author vzwingma
 *
 */
public class JwtConfig {
	
	private JwtConfig(){
		// Constructeur privé
	}
	
	public static final String JWT_AUTH_HEADER = "Authorization";

	public static final  String JWT_AUTH_PREFIX = "Bearer : ";


	public static final  int JWT_EXPIRATION_S = 86400;

	@Value("${security.jwt.secret:JwtSecretKey}")
	public static final  String JWT_SECRET_KEY = "JwtSecretKey";

	
	
	/**
	 * @param token token d'authentification
	 * @return Elements JWT
	 */
	public static Claims getJWTClaims(String token){
		token = token.replace(JwtConfig.JWT_AUTH_PREFIX, "");
		return Jwts.parser()
				.setSigningKey(JwtConfig.JWT_SECRET_KEY.getBytes())
				.parseClaimsJws(token)
				.getBody();
	}
}
