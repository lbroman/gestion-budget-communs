/**
 * 
 */
package com.terrier.finances.gestion.business.validator;

import com.terrier.finances.gestion.business.BusinessDepensesService;
import com.terrier.finances.gestion.model.business.budget.LigneDepense;
import com.terrier.finances.gestion.model.enums.TypeDepenseEnum;
import com.vaadin.data.ValidationResult;
import com.vaadin.data.Validator;
import com.vaadin.data.ValueContext;

/**
 * Validation d'une opération sur le formulaire de création
 * @author vzwingma
 *
 */
public class OperationValidator implements Validator<LigneDepense> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7063946665726387722L;


	@Override
	public ValidationResult apply(LigneDepense operation, ValueContext context) {

		// Not Null
		if(operation.getSsCategorie() == null
				|| operation.getValeurS() == null
				|| operation.getLibelle() == null
				|| operation.getTypeDepense() == null
				|| operation.getEtat() == null
				|| operation.getTypeDepense() == null){
			return ValidationResult.error("Un des éléments requis est nul");
		}

		// Valeur
		Double d =  Double.valueOf(operation.getValeurS().replaceAll(",", "."));
		if(Double.isInfinite(d) || Double.isNaN(d)){
			return ValidationResult.error("La valeur est incorrecte");
		}


		// Catégorie crédit
		if((BusinessDepensesService.ID_SS_CAT_SALAIRE.equals(operation.getSsCategorie().getId()) 
				|| BusinessDepensesService.ID_SS_CAT_REMBOURSEMENT.equals(operation.getSsCategorie().getId()))){
			if(TypeDepenseEnum.DEPENSE.equals(operation.getTypeDepense())){
				return ValidationResult.error("L'opération est un crédit. Le type doit être CREDIT");
			}
			// Sinon c'est correct
		}
		else if(TypeDepenseEnum.CREDIT.equals(operation.getTypeDepense())){
			return ValidationResult.error("L'opération est un débit. Le type doit être DEBIT (-)");
		}
		return ValidationResult.ok();
	}
}

