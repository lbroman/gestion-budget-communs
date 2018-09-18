/**
 * 
 */
package com.terrier.finances.gestion.communs.utils.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utilisateur non autorisé
 * @author vzwingma
 *
 */
public class UserNotAuthorizedException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5428709492299879225L;
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserNotAuthorizedException.class);
	
	
	/**
	 * Constructeur
	 */
	public UserNotAuthorizedException(){
		super();
	}
	
	/**
	 * Message d'erreur
	 * @param libelleErreur
	 */
	public UserNotAuthorizedException(StringBuilder libelleErreur){
		super(libelleErreur.toString());
		LOGGER.error("{}", libelleErreur);
	}
}
