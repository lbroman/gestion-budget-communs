package com.terrier.finances.gestion.communs.utils.data;

/**
 * Enum des URL d'API
 * @author vzwingma
 *
 */
public class BudgetApiUrlEnum {


	private BudgetApiUrlEnum(){
		// Constructeur privé pour une classe enum
	}
	/**
	 * Statut
	 */
	public static final String ADMIN_BASE = "/admin";	
	public static final String ADMIN_STATUT = "/v1/statut";
	public static final String ADMIN_STATUT_FULL = ADMIN_BASE + ADMIN_STATUT;
	public static final String ADMIN_PASSWORD = "/v1/password";
	public static final String ADMIN_PASSWORD_FULL = ADMIN_BASE + ADMIN_PASSWORD;
	/**
	 * Authentification / Utilisateurs
	 */
	public static final String USERS_BASE = "/utilisateurs";

	public static final String USERS_AUTHENTICATE = "/v1/authenticate";
	public static final String USERS_AUTHENTICATE_FULL = USERS_BASE + USERS_AUTHENTICATE;


	public static final String USERS_DISCONNECT = "/v1/disconnect";
	public static final String USERS_DISCONNECT_FULL = USERS_BASE + USERS_DISCONNECT;

	public static final String USERS_ACCESS_DATE = "/v1/lastaccessdate";
	public static final String USERS_ACCESS_DATE_FULL = USERS_BASE + USERS_ACCESS_DATE;
	public static final String USERS_PREFS = "/v1/preferences";
	public static final String USERS_PREFS_FULL = USERS_BASE + USERS_PREFS;

	/**
	 * Comptes
	 */
	public static final String COMPTES_BASE = "/comptes";
	public static final String COMPTES_LIST = "/v1/comptes";
	public static final String COMPTES_LIST_FULL = COMPTES_BASE + COMPTES_LIST;

	public static final String COMPTES_ID = "/v1";
	public static final String COMPTES_ID_FULL = COMPTES_BASE + COMPTES_ID;
	
	/**
	 * Paramétrages
	 */
	public static final String PARAMS_BASE = "/parametres";
	public static final String PARAMS_CATEGORIES = "/v1/categories";
	public static final String PARAMS_CATEGORIES_FULL = PARAMS_BASE + PARAMS_CATEGORIES;
}
