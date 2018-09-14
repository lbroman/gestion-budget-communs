
package com.terrier.finances.gestion.communs.budget.model;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.terrier.finances.gestion.communs.abstrait.AbstractAPIObjectModel;
import com.terrier.finances.gestion.communs.api.converter.CategorieOperationsKeySerializer;
import com.terrier.finances.gestion.communs.comptes.model.CompteBancaire;
import com.terrier.finances.gestion.communs.operations.model.LigneOperation;
import com.terrier.finances.gestion.communs.operations.model.enums.TypeOperationEnum;
import com.terrier.finances.gestion.communs.parametrages.model.CategorieOperation;
import com.terrier.finances.gestion.communs.parametrages.model.enums.IdsCategoriesEnum;

/**
 * Budget du mois
 * @author vzwingma
 *
 */
public class BudgetMensuel extends AbstractAPIObjectModel {

	private String id;
	/**
	 * 
	 */
	private static final long serialVersionUID = 4393433203514049021L;

	@JsonIgnore
	private static final transient Logger LOGGER = LoggerFactory.getLogger( BudgetMensuel.class );
	/**
	 * Mois du budget
	 */
	private transient Month mois;
	private int annee;
	/**
	 * Budget actif
	 */
	private boolean actif = false;
	/**
	 * Date de mise à jour
	 */
	private Calendar dateMiseAJour;
	/**
	 * Compte bancaire
	 */
	private CompteBancaire compteBancaire;
	/**
	 * Résultat du mois précédent
	 */
	private Double moisPrecedentResultat;

	private Double moisPrecedentMarge = 0D;

	/**
	 * Liste des dépenses
	 */
	@JsonIgnore
	private List<LigneOperation> listeOperations = new ArrayList<>();

	private transient boolean isNewBudget = false;

	private Map<CategorieOperation, Double[]> totalParCategories = new HashMap<>();

	private Map<CategorieOperation, Double[]> totalParSSCategories = new HashMap<>();

	/**
	 * Totaux
	 */
	private Double soldeNow;
	private Double soldeFin;

	/**
	 * Raz calculs
	 */
	public void razCalculs(){
		totalParCategories.clear();
		totalParSSCategories.clear();
		soldeNow = this.moisPrecedentResultat;
		soldeFin = this.moisPrecedentResultat;
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
	 * @return the totalParCategories
	 */
	@JsonSerialize(keyUsing = CategorieOperationsKeySerializer.class) 
	public Map<CategorieOperation, Double[]> getTotalParCategories() {
		return totalParCategories;
	}

	/**
	 * @return the totalParSSCategories
	 */
	@JsonSerialize(keyUsing = CategorieOperationsKeySerializer.class)  
	public Map<CategorieOperation, Double[]> getTotalParSSCategories() {
		return totalParSSCategories;
	}

	/**
	 * @return the nowArgentAvance
	 */
	public double getSoldeNow() {
		return soldeNow;
	}

	/**
	 * @return the nowCompteReel
	 */
	@JsonIgnore
	public double getSoldeReelNow() {
		return soldeNow + getMarge();
	}

	/**
	 * @return the finArgentAvance
	 */
	public double getSoldeFin() {
		return soldeFin;
	}

	/**
	 * @return the finCompteReel
	 */
	@JsonIgnore
	public double getSoldeReelFin() {
		return soldeFin + getMarge();
	}

	/**
	 * @return the mois
	 */
	public Month getMois() {
		return mois;
	}

	/**
	 * @return the annee
	 */
	public int getAnnee() {
		return annee;
	}

	/**
	 * @param annee the annee to set
	 */
	public void setAnnee(int annee) {
		this.annee = annee;
	}


	/**
	 * @param mois the mois to set
	 */
	public void setMois(Month mois) {
		this.mois = mois;
	}

	/**
	 * @param nowArgentAvance the nowArgentAvance to set
	 */
	public void ajouteASoldeNow(double nowArgentAvance) {
		this.soldeNow += nowArgentAvance;
	}

	/**
	 * @param finArgentAvance the finArgentAvance to set
	 */
	public void ajouteASoldeFin(double finArgentAvance) {
		this.soldeFin += finArgentAvance;
	}


	/**
	 * @return the resultatMoisPrecedent
	 */
	public Double getMoisPrecedentResultat() {
		return moisPrecedentResultat;
	}

	/**
	 * Mise à jour des valeurs depuis le mois précédent
	 * @param resultatMoisPrecedent the resultatMoisPrecedent to set
	 * @param margeMoisPrecedent marge mois précédent
	 */
	public void setResultatMoisPrecedent(Double resultatMoisPrecedent, Double margeMoisPrecedent) {
		this.moisPrecedentResultat = resultatMoisPrecedent;
		this.soldeFin = resultatMoisPrecedent;
		this.soldeNow = resultatMoisPrecedent;
		this.moisPrecedentMarge = margeMoisPrecedent;
		this.margeCalculee = margeMoisPrecedent;
	}

	/**
	 * @return the listeOperations
	 */
	public List<LigneOperation> getListeOperations() {
		return listeOperations;
	}

	/**
	 * @param listeOperations the listeOperations to set
	 */
	public void setListeOperations(List<LigneOperation> listeOperations) {
		this.listeOperations = listeOperations;
	}


	/**
	 * @return the margeSecurite
	 */
	public Double getMoisPrecedentMarge() {
		return moisPrecedentMarge;
	}


	private Double margeCalculee;
	/**
	 * @return the margeSecurite
	 */
	@JsonIgnore
	public Double getMarge() {
		margeCalculee = this.moisPrecedentMarge;
		try{
			if(this.listeOperations != null && !this.listeOperations.isEmpty()){
				this.listeOperations.stream()
				.filter(op -> op.getSsCategorie() != null && IdsCategoriesEnum.RESERVE.getId().equals(op.getSsCategorie().getId()))
				.forEach(op -> {
					int type = TypeOperationEnum.CREDIT.equals(op.getTypeDepense()) ? 1 : -1;
					margeCalculee = margeCalculee + type * Double.valueOf(op.getValeur());
				});
			}
		}
		catch(Exception e){
			LOGGER.warn("Erreur lors du calcul de la marge à partir des opérations {}. La marge du mois précédent est reportée [{}]", this.listeOperations, this.moisPrecedentMarge, e);
		}
		return margeCalculee;
	}

	/**
	 * @return the dateMiseAJour
	 */
	public Calendar getDateMiseAJour() {
		return dateMiseAJour;
	}


	/**
	 * @param dateMiseAJour the dateMiseAJour to set
	 */
	public void setDateMiseAJour(Calendar dateMiseAJour) {
		this.dateMiseAJour = dateMiseAJour;
	}


	/**
	 * @return the compteBancaire
	 */
	public CompteBancaire getCompteBancaire() {
		return compteBancaire;
	}

	/**
	 * @param compteBancaire the compteBancaire to set
	 */
	public void setCompteBancaire(CompteBancaire compteBancaire) {
		this.compteBancaire = compteBancaire;
	}

	/**
	 * @param totalParCategories the totalParCategories to set
	 */
	public void setTotalParCategories(
			Map<CategorieOperation, Double[]> totalParCategories) {
		this.totalParCategories = totalParCategories;
	}

	/**
	 * @param totalParSSCategories the totalParSSCategories to set
	 */
	public void setTotalParSSCategories(
			Map<CategorieOperation, Double[]> totalParSSCategories) {
		this.totalParSSCategories = totalParSSCategories;
	}

	/**
	 * @param soldeNow the soldeNow to set
	 */
	public void setSoldeNow(Double soldeNow) {
		this.soldeNow = soldeNow;
	}


	/**
	 * @param soldeFin the soldeFin to set
	 */
	public void setSoldeFin(Double soldeFin) {
		this.soldeFin = soldeFin;
	}


	/**
	 * @return the actif
	 */
	public boolean isActif() {
		return actif;
	}

	/**
	 * @param actif the actif to set
	 */
	public void setActif(boolean actif) {
		this.actif = actif;
	}


	/**
	 * @return the isNewBudget
	 */
	public boolean isNewBudget() {
		return isNewBudget;
	}

	/**
	 * @param isNewBudget the isNewBudget to set
	 */
	public void setNewBudget(boolean isNewBudget) {
		this.isNewBudget = isNewBudget;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "BudgetMensuel [mois=" + mois + ", annee=" + annee
				+ ", bugetActif=" + actif + ", dateMiseAJour="
				+ (dateMiseAJour != null ? dateMiseAJour.getTime() : "null") + ", compte=" + compteBancaire + "]";
	}
}