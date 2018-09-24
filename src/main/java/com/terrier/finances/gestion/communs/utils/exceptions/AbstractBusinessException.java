package com.terrier.finances.gestion.communs.utils.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Exeption métier
 * @author vzwingma
 *
 */
public class AbstractBusinessException extends Exception {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8869692972880299979L;
	/**
	 * Logger
	 */
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public AbstractBusinessException(String libelleErreur){
		logger.error("{}", libelleErreur);
	}
}
