/**
 * 
 */
package com.terrier.finances.gestion.communs.comptes.model;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.terrier.finances.gestion.communs.abstrait.AbstractAPIObjectModel;
import com.terrier.finances.gestion.communs.utilisateur.model.Utilisateur;

/**
 * Compte bancaire
 * @author vzwingma
 *
 */
@Document(collection = "comptesbancaires")
public class CompteBancaire extends AbstractAPIObjectModel {

	// SId
	private static final long serialVersionUID = -846392155444814540L;

	@Id
	private String id;
		
	// Libellé du compte
	private String libelle;
	// Liste des propriétaires du compte
	@JsonIgnore
	private List<Utilisateur> listeProprietaires;
	// Icone
	private String itemIcon;
	// N° d'ordre
	private int ordre;
	// closed
	private Boolean actif;
	

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the listeProprietaires
	 */
	public List<Utilisateur> getListeProprietaires() {
		return listeProprietaires;
	}

	/**
	 * @param listeProprietaires the listeProprietaires to set
	 */
	public void setListeProprietaires(List<Utilisateur> listeProprietaires) {
		this.listeProprietaires = listeProprietaires;
	}

	/**
	 * @return the itemIcon
	 */
	public String getItemIcon() {
		return itemIcon;
	}

	/**
	 * @param itemIcon the itemIcon to set
	 */
	public void setItemIcon(String itemIcon) {
		this.itemIcon = itemIcon;
	}
	
	

	

	/**
	 * @return the actif
	 */
	public Boolean isActif() {
		// Vrai par défaut
		return actif != null ? actif : Boolean.TRUE;
	}

	/**
	 * @param actif the actif to set
	 */
	public void setActif(Boolean actif) {
		this.actif = actif;
	}

	/**
	 * @return the ordre
	 */
	public int getOrdre() {
		return ordre;
	}

	/**
	 * @param ordre the ordre to set
	 */
	public void setOrdre(int ordre) {
		this.ordre = ordre;
	}


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("CompteBancaire [id=").append(id).append(", libelle=").append(libelle).append("]");
		return builder.toString();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof CompteBancaire)) {
			return false;
		}
		CompteBancaire other = (CompteBancaire) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}
	
	
}
