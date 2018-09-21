package com.terrier.finances.gestion.communs.api.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

/**
 * Config JWT
 * @author vzwingma
 *
 */
public class JwtConfig {
	
	private JwtConfig(){
		// Constructeur privé
	}

	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(JwtConfig.class);
	
	public static final String JWT_AUTH_HEADER = "Authorization";

	public static final  String JWT_AUTH_PREFIX = "Bearer : ";

	public static final String JWT_CLAIM_USERID_HEADER = "USERID";
	
	public static final String JWT_CLAIM_AUTORITIES_HEADER = "authorities";

	public static final  int JWT_EXPIRATION_S = 3600;

	@Value("${security.jwt.secret:JwtSecretKey}")
	public static final  String JWT_SECRET_KEY = "JwtSecretKey";

	
	
	/**
	 * @param token token d'authentification
	 * @return Elements JWT
	 */
	public static Claims getJWTClaims(String token){
		token = token.replace(JwtConfig.JWT_AUTH_PREFIX, "");
		try {
			Jws<Claims> jwtClaims = Jwts.parser()
					.setSigningKey(JwtConfig.JWT_SECRET_KEY.getBytes())
					.parseClaimsJws(token);
					return jwtClaims.getBody();
		}
		catch (ExpiredJwtException e) {
			LOGGER.error("[SEC] Le token [{}] est expiré : {}", token, e.getMessage());
			throw e;
		}
		catch (SignatureException e) {
			LOGGER.error("[SEC] Le token [{}] est mal signé : {}", token, e.getMessage());
			throw e;
		}
		catch (UnsupportedJwtException | MalformedJwtException e) {
			LOGGER.error("[SEC] Le token [{}] est incorrect : {}", token, e.getMessage());
			throw e;
		}		
	}
}
