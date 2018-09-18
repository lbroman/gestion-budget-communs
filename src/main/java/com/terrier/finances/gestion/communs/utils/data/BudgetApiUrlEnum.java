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
	public static final String ADMIN_ACCESS = "/v1/password/{oldpassword}/{newpassword}";
	public static final String ADMIN_ACCESS_FULL = ADMIN_BASE + ADMIN_ACCESS;
	/**
	 * Authentification / Utilisateurs
	 */
	public static final String USERS_BASE = "/utilisateurs/v1";

	public static final String USERS_AUTHENTICATE = "/authenticate";
	public static final String USERS_AUTHENTICATE_FULL = USERS_BASE + USERS_AUTHENTICATE;


	public static final String USERS_DISCONNECT = "/disconnect";
	public static final String USERS_DISCONNECT_FULL = USERS_BASE + USERS_DISCONNECT;

	public static final String USERS_ACCESS_DATE = "/lastaccessdate";
	public static final String USERS_ACCESS_DATE_FULL = USERS_BASE + USERS_ACCESS_DATE;
	public static final String USERS_PREFS = "/preferences";
	public static final String USERS_PREFS_FULL = USERS_BASE + USERS_PREFS;

	/**
	 * Comptes
	 */
	public static final String COMPTES_BASE = "/comptes/v1";
	public static final String COMPTES_LIST = "";
	public static final String COMPTES_LIST_FULL = COMPTES_BASE + COMPTES_LIST;

	public static final String COMPTES_ID = "/{idCompte}";
	public static final String COMPTES_ID_FULL = COMPTES_BASE + COMPTES_ID;

	public static final String COMPTES_INTERVALLES = "/{idCompte}/intervalles";
	public static final String COMPTES_INTERVALLES_FULL = COMPTES_BASE + COMPTES_INTERVALLES;
	
	public static final String COMPTES_OPERATIONS_LIBELLES = "/{idCompte}/operations/libelles";
	public static final String COMPTES_OPERATIONS_LIBELLES_FULL = COMPTES_BASE + COMPTES_OPERATIONS_LIBELLES;
	
	
	/**
	 * Paramétrages
	 */
	public static final String PARAMS_BASE = "/parametres/v1";
	public static final String PARAMS_CATEGORIES = "/categories";
	public static final String PARAMS_CATEGORIES_FULL = PARAMS_BASE + PARAMS_CATEGORIES;
	
	
	/**
	 * Budget
	 */
	public static final String BUDGET_BASE = "/budgets/v1";	
	public static final String BUDGET_ID = "/{idBudget}";
	public static final String BUDGET_ID_FULL = BUDGET_BASE + BUDGET_ID;

	/*
	 * Avec en paramètre ?idCompte={idCompte}&mois={mois}&annee={annee}
	 */
	public static final String BUDGET_QUERY = "/query"; 
	public static final String BUDGET_QUERY_FULL = BUDGET_BASE + BUDGET_QUERY;
	/*
	 * Avec en paramètres :  ?actif=true  ou ?uptodateto=long
	 */
	public static final String BUDGET_ETAT = "/{idBudget}/etat"; 
	public static final String BUDGET_ETAT_FULL = BUDGET_BASE + BUDGET_ETAT;
}
