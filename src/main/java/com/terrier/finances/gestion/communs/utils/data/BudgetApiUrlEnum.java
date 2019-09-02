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
	
	public static final String URL_PARAM_ID_BUDGET = "{idBudget}";
	public static final String URL_PARAM_ID_COMPTE = "{idCompte}";
	public static final String URL_PARAM_ID_OPERATION = "{idOperation}";
	
	/**
	 * Actuators
	 */
	public static final String ACTUATORS_BASE = "/actuator";		
	public static final String ACTUATORS_INFO = "/info";
	public static final String ACTUATORS_INFO_FULL = ACTUATORS_BASE + ACTUATORS_INFO;
	/**
	 * Statut
	 */
	public static final String ADMIN_BASE = "/admin";	
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

	public static final String COMPTES_ID = "/"+URL_PARAM_ID_COMPTE;
	public static final String COMPTES_ID_FULL = COMPTES_BASE + COMPTES_ID;

	public static final String COMPTES_INTERVALLES = "/"+URL_PARAM_ID_COMPTE+"/intervalles";
	public static final String COMPTES_INTERVALLES_FULL = COMPTES_BASE + COMPTES_INTERVALLES;
	
	public static final String COMPTES_OPERATIONS_LIBELLES = "/"+URL_PARAM_ID_COMPTE+"/operations/libelles";
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
	public static final String BUDGET_ID = "/"+ URL_PARAM_ID_BUDGET;
	public static final String BUDGET_ID_FULL = BUDGET_BASE + BUDGET_ID;

	// Avec en paramètre
	// - idCompte={idCompte}
	// - mois={mois}
	// - annee={annee}
	public static final String BUDGET_QUERY = "/query"; 
	public static final String BUDGET_QUERY_FULL = BUDGET_BASE + BUDGET_QUERY;
	// Avec en paramètres : 
	// - actif=true
	/// ou
	// - uptodateto=long
	public static final String BUDGET_ETAT = "/"+URL_PARAM_ID_BUDGET+"/etat"; 
	public static final String BUDGET_ETAT_FULL = BUDGET_BASE + BUDGET_ETAT;
	
	/**
	 * Operations
	 */
	public static final String BUDGET_OPERATION = BUDGET_ID + "/operations/"+URL_PARAM_ID_OPERATION;
	public static final String BUDGET_OPERATION_FULL = BUDGET_BASE + BUDGET_OPERATION;	

	public static final String BUDGET_OPERATION_DERNIERE = BUDGET_OPERATION + "/derniereOperation";
	public static final String BUDGET_OPERATION_DERNIERE_FULL = BUDGET_BASE + BUDGET_OPERATION_DERNIERE;
	
	public static final String BUDGET_OPERATION_INTERCOMPTE = BUDGET_OPERATION + "/versCompte/"+URL_PARAM_ID_COMPTE;
	public static final String BUDGET_OPERATION_INTERCOMPTE_FULL = BUDGET_BASE + BUDGET_OPERATION_INTERCOMPTE;
}
