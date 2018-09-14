package com.terrier.finances.gestion.communs.utils.data;

import java.time.LocalDate;
import java.time.Month;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import com.terrier.finances.gestion.communs.comptes.model.CompteBancaire;
import com.terrier.finances.gestion.communs.operations.model.LigneOperation;

/**
 * Utilitaire de data
 * @author vzwingma
 *
 */
public class BudgetDataUtils {

	
	private BudgetDataUtils(){
		// constructeur privé
	}
	
	/**
	 * @param compte compte bancaire
	 * @param mois mois
	 * @param annee année
	 * @return id de budget
	 */
	public static String getBudgetId(CompteBancaire compte, Month mois, int annee){
		return new StringBuilder().append(compte.getId()).append("_").append(annee).append("_").append(mois.getValue()).toString();
	}
	/**
	 * @param budgetId id budget
	 * @return la valeur de l'année à partir de l'id
	 */
	public static Month getMoisFromBudgetId(String budgetId){
		if(budgetId != null){
			return Month.of(Integer.parseInt(budgetId.substring(budgetId.lastIndexOf('_') + 1, budgetId.length())));
		}
		return null;
	}
	/**
	 * @param budgetId id budget
	 * @return la valeur de l'année à partir de l'id
	 */
	public static String getCompteFromBudgetId(String budgetId){
		if(budgetId != null){
			return budgetId.substring(0, budgetId.indexOf('_'));
		}
		return null;
	}
	
	/**
	 * @param budgetId id budget
	 * @return la valeur de l'année à partir de l'id
	 */
	public static Integer getAnneeFromBudgetId(String budgetId){
		if(budgetId != null){
			return Integer.parseInt(budgetId.substring(budgetId.indexOf('_') + 1, budgetId.lastIndexOf('_')));
		}
		return null;
	}
	/**
	 * @param listeOperations liste des opérations
	 * @return date max d'une liste de dépenses
	 */
	public static LocalDate getMaxDateListeOperations(List<LigneOperation> listeOperations){

		LocalDate localDateDerniereOperation = BudgetDateTimeUtils.localDateNow();

		if(listeOperations != null && !listeOperations.isEmpty()){
			// Comparaison de date
			Comparator <LigneOperation> comparator = Comparator.comparing(LigneOperation::getDateOperation, (date1, date2) -> {
				if(date1 == null){
					return 1;
				}
				else if(date2 == null){
					return -1;
				}
				else{
					return date1.before(date2) ? -1 : 1;
				}
			});
			Optional<LigneOperation> maxDate = listeOperations.stream().max(comparator);
			if(maxDate.isPresent() && maxDate.get().getDateOperation() != null){
				localDateDerniereOperation = BudgetDateTimeUtils.asLocalDate(maxDate.get().getDateOperation());
			}
		}
		return localDateDerniereOperation;
	}


	/**
	 * @param valeurS valeur en String
	 * @return la valeur d'un String en double
	 */
	public static String getValueFromString(String valeurS){

		if(valeurS != null){
			valeurS = valeurS.replaceAll(",", ".");
			try{
				valeurS = Double.toString(Double.valueOf(valeurS));
			}
			catch(Exception e){
				valeurS = null;
			}
		}
		return valeurS;
	}
}
