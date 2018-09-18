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
public class UserAccessForbiddenException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5428709492299879225L;
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(UserAccessForbiddenException.class);
	
	
	/**
	 * Constructeur
	 */
	public UserAccessForbiddenException(){
		super();
	}
	
	/**
	 * Message d'erreur
	 * @param libelleErreur
	 */
	public UserAccessForbiddenException(StringBuilder libelleErreur){
		super(libelleErreur.toString());
		LOGGER.error("{}", libelleErreur);
	}
}
