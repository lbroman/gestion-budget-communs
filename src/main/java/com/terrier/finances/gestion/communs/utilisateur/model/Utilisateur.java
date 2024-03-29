/**
 * 
 */
package com.terrier.finances.gestion.communs.utilisateur.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.EnumMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.terrier.finances.gestion.communs.utilisateur.enums.UtilisateurDroitsEnum;
import com.terrier.finances.gestion.communs.utilisateur.enums.UtilisateurPrefsEnum;
import com.terrier.finances.gestion.communs.utils.data.BudgetDateTimeUtils;

/**
 * Définition d'un utilisateur de la BDD
 * @author vzwingma
 *
 */
@Document(collection = "utilisateurs")
public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6535669274718528190L;
	
	@Id
	private String id;
	// Login
	private String login;
	
	// Mot de passe
	private String password;

	private LocalDateTime dernierAcces;
	
	// Libellé
	private String libelle;
	/**
	 * Préférences
	 */
	private Map<UtilisateurPrefsEnum, String> prefsUtilisateur = new EnumMap<>(UtilisateurPrefsEnum.class);
	/**
	 * Droits
	 */
	private Map<UtilisateurDroitsEnum, Boolean> droits = new EnumMap<>(UtilisateurDroitsEnum.class);
	
	
	/**
	 * @return the login
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @param login the login to set
	 */
	public void setLogin(String login) {
		this.login = login;
	}

	/**
	 * @return the libelle
	 */
	public String getLibelle() {
		return libelle;
	}

	/**
	 * @param libelle the libelle to set
	 */
	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	/**
	 * @return the prefsUtilisateur
	 */
	public Map<UtilisateurPrefsEnum, String> getPrefsUtilisateur() {
		return prefsUtilisateur;
	}

	/**
	 * @param prefsUtilisateur the prefsUtilisateur to set
	 */
	public void setPrefsUtilisateur(Map<UtilisateurPrefsEnum, String> prefsUtilisateur) {
		this.prefsUtilisateur = prefsUtilisateur;
	}


	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @param clePreference clé d'une préférence
	 * @param <T> Type de la préférence
	 * @return the preferences
	 */
	@SuppressWarnings("unchecked")
	public <T> T getPreference(UtilisateurPrefsEnum clePreference) {
		return (T)prefsUtilisateur.get(clePreference);
	}

	/**
	 * @return the dernierAcces
	 */
	public LocalDateTime getDernierAcces() {
		return dernierAcces;
	}

	/**
	 * @param dernierAcces the dernierAcces to set
	 */
	public void setDernierAcces(LocalDateTime dernierAcces) {
		this.dernierAcces = dernierAcces;
	}

	/**
	 * @return the droits
	 */
	public Map<UtilisateurDroitsEnum, Boolean> getDroits() {
		return droits;
	}

	/**
	 * @param droits the droits to set
	 */
	public void setDroits(Map<UtilisateurDroitsEnum, Boolean> droits) {
		this.droits = droits;
	}
	
	

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	
	
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.login;
	}
	
	public String toFullString(){
		StringBuilder builder = new StringBuilder();
		builder.append("Utilisateur [login=").append(login)
				.append(", dateDernierAcces=")
				.append(dernierAcces != null ? BudgetDateTimeUtils.getLibelleDate(dernierAcces) : "nulle").append(", libelle=").append(libelle)
				.append("]");
		return builder.toString();
	}

}
