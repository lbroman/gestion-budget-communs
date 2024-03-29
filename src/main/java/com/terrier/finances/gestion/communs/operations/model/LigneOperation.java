package com.terrier.finances.gestion.communs.operations.model;

import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.terrier.finances.gestion.communs.abstrait.AbstractAPIObjectModel;
import com.terrier.finances.gestion.communs.operations.model.enums.EtatOperationEnum;
import com.terrier.finances.gestion.communs.operations.model.enums.TypeOperationEnum;
import com.terrier.finances.gestion.communs.parametrages.model.CategorieOperation;
import com.terrier.finances.gestion.communs.utils.data.BudgetDataUtils;

/**
 * 
 * Ligne d'opération dans un budget mensuel
 * @author vzwingma
 *
 */
public class LigneOperation extends AbstractAPIObjectModel implements Comparable<LigneOperation> {

	//
	private static final long serialVersionUID = -5020058513824102750L;
	// Id
	private String id;
	// SS Catégorie
	private String idSsCategorie;

	// SS Catégorie
	@JsonIgnore
	private CategorieOperation ssCategorie;

	// Libellé
	private String libelle;
	// Type de dépense
	private TypeOperationEnum typeDepense;
	// Etat de la ligne
	private EtatOperationEnum etat;
	// Valeur
	private double valeur;
	// Date operation
	private Date dateOperation;
	// Date mise à jour
	private Date dateMaj;
	// Auteur MAJ
	private String auteur;
	// Périodique
	private boolean periodique; 
	// tag comme dernière opération
	private boolean derniereOperation;

	/**
	 * Logger
	 */
	@JsonIgnore
	private static final Logger LOGGER = LoggerFactory.getLogger(LigneOperation.class);

	/**
	 * Constructeur par défaut
	 */
	public LigneOperation(){
		// Constructeur pour Spring		
	}

	/**
	 * Constructeur
	 * @param ssCategorie Catégorie
	 * @param libelle libellé
	 * @param typeDepense type d'opération
	 * @param absValeur valeur montant en valeur absolue
	 * @param etat état
	 * @param periodique périodicité de l'opération
	 */
	public LigneOperation(CategorieOperation ssCategorie, String libelle, TypeOperationEnum typeDepense, String absValeur, EtatOperationEnum etat, boolean periodique){
		this.id = UUID.randomUUID().toString();
		setSsCategorie(ssCategorie);
		this.libelle = libelle;
		this.typeDepense = typeDepense;
		setValeurAbsStringToDouble(absValeur);
		this.etat = etat;
		this.dateOperation = Calendar.getInstance().getTime();
		this.periodique = periodique;
		this.derniereOperation = false;
	}


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
	 * @return the idSsCategorie
	 */
	public String getIdSsCategorie() {
		return idSsCategorie;
	}

	/**
	 * @return the ssCategorie
	 */
	@JsonIgnore
	public CategorieOperation getSsCategorie() {
		return ssCategorie;
	}
	/**
	 * @return the ssCategorie
	 */

	public String getIdCategorie() {
		if(ssCategorie != null && ssCategorie.getCategorieParente() != null) {
			return ssCategorie.getCategorieParente().getId();
		}
		return null;
	}

	/**
	 * @return the ssCategorie
	 */
	@JsonIgnore
	public CategorieOperation getCategorie() {
		return ssCategorie.getCategorieParente();
	}

	/**
	 * @param categorie the categorie to set
	 */
	@JsonIgnore
	public void setCategorie(CategorieOperation categorie) {
		// Ne fait rien, calculé par la sous catégorie
	}

	/**
	 * @param ssCategorie the ssCategorie to set
	 */
	@JsonIgnore
	public void setSsCategorie(CategorieOperation ssCategorie) {
		this.ssCategorie = ssCategorie;
		this.idSsCategorie = ssCategorie.getId();
	}

	/**
	 * @param idSsCategorie the idSsCategorie to set
	 */
	public void setIdSsCategorie(String idSsCategorie) {
		this.idSsCategorie = idSsCategorie;
	}
	/**
	 * @param idCategorie the idCategorie to set
	 */
	public void setIdCategorie(String idCategorie) {
		// Rien à faire. Setté par ssCategorie
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
	 * @return the typeDepense
	 */
	public TypeOperationEnum getTypeDepense() {
		return typeDepense;
	}
	/**
	 * @param typeDepense the typeDepense to set
	 */
	public void setTypeDepense(TypeOperationEnum typeDepense) {
		this.typeDepense = typeDepense;
	}
	/**
	 * @return the valeur
	 */
	public double getValeur() {
		return valeur;
	}

	/**
	 * @return valeur absolue pour editor
	 */
	@JsonIgnore
	public String getValeurAbsStringFromDouble() {
		return Double.toString(Math.abs(valeur));
	}

	/**
	 * @param valeurS set valeur depuis l'editor
	 */
	@JsonIgnore
	public void setValeurAbsStringToDouble(String valeurS){
		valeurS = BudgetDataUtils.getValueFromString(valeurS);
		if(valeurS != null){
			this.valeur = Math.abs(Double.parseDouble(valeurS)) * (TypeOperationEnum.DEPENSE.equals(this.getTypeDepense()) ? -1 : 1);
		}
	}

	/**
	 * @return the dateOperation
	 */
	public Date getDateOperation() {
		return dateOperation;
	}

	/**
	 * @param dateOperation the dateOperation to set
	 */
	public void setDateOperation(Date dateOperation) {
		this.dateOperation = dateOperation;
	}

	/**
	 * @return the dateMaj
	 */
	public Date getDateMaj() {
		return dateMaj;
	}

	/**
	 * @param dateMaj the dateMaj to set
	 */
	public void setDateMaj(Date dateMaj) {
		this.dateMaj = dateMaj;
	}

	/**
	 * @return the auteur
	 */
	public String getAuteur() {
		return auteur;
	}

	/**
	 * @param auteur the auteur to set
	 */
	public void setAuteur(String auteur) {
		this.auteur = auteur;
	}

	/**
	 * @return the etat
	 */
	public EtatOperationEnum getEtat() {
		return etat;
	}

	/**
	 * @param etat the etat to set
	 */
	public void setEtat(EtatOperationEnum etat) {
		this.etat = etat;
	}

	/**
	 * @return the periodique
	 */
	public boolean isPeriodique() {
		return periodique;
	}

	/**
	 * @param periodique the periodique to set
	 */
	public void setPeriodique(boolean periodique) {
		this.periodique = periodique;
	}


	/**
	 * @param periodique the periodique to set
	 */
	public void setPeriodique(Boolean periodique) {
		this.periodique = periodique;
	}

	/**
	 * @return the derniereOperation
	 */
	public boolean isDerniereOperation() {
		return derniereOperation;
	}

	/**
	 * @param derniereOperation the derniereOperation to set
	 */
	public void setDerniereOperation(boolean derniereOperation) {
		this.derniereOperation = derniereOperation;
	}	


	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("LigneOperation [uuid=").append(id).append(", idSsCategorie=").append(idSsCategorie).append(", libelle=")
		.append(libelle).append(", typeDepense=").append(typeDepense).append(", etat=").append(etat)
		.append(", valeur=").append(valeur).append(", dateOperation=").append(dateOperation)
		.append(", dateMaj=").append(dateMaj).append(", auteur=").append(auteur).append(", periodique=")
		.append(periodique).append(", derniereOperation=").append(derniereOperation).append("]");
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
		if (!(obj instanceof LigneOperation)) {
			return false;
		}
		LigneOperation other = (LigneOperation) obj;
		if (id == null) {
			if (other.id != null) {
				return false;
			}
		} else if (!id.equals(other.id)) {
			return false;
		}
		return true;
	}

	/* (non-Javadoc)
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(LigneOperation o) {
		if(o != null){
			return this.getId().compareTo(o.getId());
		}
		return 0;
	}
}
