package com.terrier.finances.gestion.communs.parametrages.model;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.terrier.finances.gestion.communs.abstrait.AbstractAPIObjectModel;
import com.terrier.finances.gestion.communs.parametrages.model.enums.IdsCategoriesEnum;

/**
 * Catégorie d'opérations
 * @author vzwingma
 *
 */
public class CategorieOperation extends AbstractAPIObjectModel implements Comparable<CategorieOperation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1755547774539137070L;
	/**
	 * Identifiant
	 */
	@Id
	private String id;
	/**
	 * Libelle
	 */
	private String libelle;
	/**
	 * Actif
	 */	
	private boolean actif;
	/**
	 * Liste des sous catégories
	 */	
	private Set<CategorieOperation> listeSSCategories = new HashSet<>();

	/**
	 * Catégorie
	 */
	@JsonIgnore
	private CategorieOperation categorieParente;

	/**
	 * Est ce une catégorie ?
	 */
	private boolean categorie = true;


	/**
	 * Constructeur pour Spring Data MongSB
	 */
	public CategorieOperation(){
		this.id = UUID.randomUUID().toString();
	}
	
	/**
	 * Constructeur pour le clone
	 * @param id id du parent
	 */
	public CategorieOperation(String id){
		this.id = id;
	}


	public CategorieOperation(IdsCategoriesEnum typeCategorie){
		this.id = typeCategorie.getId();
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
	 * @return the listeSSCategories
	 */
	public Set<CategorieOperation> getListeSSCategories() {
		return listeSSCategories;
	}


	/**
	 * @return the categorieParente
	 */
	public CategorieOperation getCategorieParente() {
		return categorieParente;
	}


	/**
	 * @return the categorie
	 */
	public boolean isCategorie() {
		return categorie;
	}

	/**
	 * @param categorie the categorie to set
	 */
	public void setCategorie(boolean categorie) {
		this.categorie = categorie;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.libelle;
	}


	/**
	 * @return the idf
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
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}





	/**
	 * @param categorieParente the categorieParente to set
	 */
	public void setCategorieParente(CategorieOperation categorieParente) {
		this.categorieParente = categorieParente;
	}




	/**
	 * @param actif the actif to set
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}




	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((libelle == null) ? 0 : libelle.hashCode());
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
		if (!(obj instanceof CategorieOperation)) {
			return false;
		}
		CategorieOperation other = (CategorieOperation) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		if (libelle == null) {
			if (other.libelle != null) {
				return false;
			}
		} else if (!libelle.equals(other.libelle)) {
			return false;
		}
		return true;
	}





	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(CategorieOperation o) {
		if(o != null){
			return this.libelle.compareTo(o.getLibelle());
		}
		return 0;
	}
}
