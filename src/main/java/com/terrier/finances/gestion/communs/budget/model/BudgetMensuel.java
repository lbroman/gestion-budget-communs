			
package com.terrier.finances.gestion.communs.budget.model;

import java.time.Month;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.terrier.finances.gestion.communs.abstrait.AbstractAPIObjectModel;
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
	/**
	 * Logger
	 */
	private static final Logger LOGGER = LoggerFactory.getLogger(BudgetMensuel.class);
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

	private Double margeMoisPrecedent = 0D;

	/**
	 * Liste des dépenses
	 */
	private List<LigneOperation> listeOperations = new ArrayList<>();
	/** 
	 * Liste des libellés pour l'autocomplétion
	 */
	private transient Set<String> setLibellesDepensesForAutocomplete= new TreeSet<>();

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
		LOGGER.debug("Raz des calculs du budget");
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
	public Map<CategorieOperation, Double[]> getTotalParCategories() {
		return totalParCategories;
	}

	/**
	 * @return the totalParSSCategories
	 */
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
		this.margeMoisPrecedent = margeMoisPrecedent;
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
		return margeMoisPrecedent;
	}
	
	
	private Double margeCalculee;
	/**
	 * @return the margeSecurite
	 */
	public Double getMarge() {
		margeCalculee = this.margeMoisPrecedent;
		this.listeOperations.stream()
			.filter(op -> IdsCategoriesEnum.RESERVE.getId().equals(op.getSsCategorie().getId()))
			.forEach(op -> {
				int type = TypeOperationEnum.CREDIT.equals(op.getTypeDepense()) ? 1 : -1;
				margeCalculee = margeCalculee + type * Double.valueOf(op.getValeur());
			});
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
	 * @return the listeLibellesDepenses
	 */
	public Set<String> getSetLibellesDepensesForAutocomplete() {
		return setLibellesDepensesForAutocomplete;
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