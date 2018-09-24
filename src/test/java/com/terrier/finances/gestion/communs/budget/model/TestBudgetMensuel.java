package com.terrier.finances.gestion.communs.budget.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import com.terrier.finances.gestion.communs.operations.model.LigneOperation;
import com.terrier.finances.gestion.communs.operations.model.enums.TypeOperationEnum;
import com.terrier.finances.gestion.communs.parametrages.model.CategorieOperation;
import com.terrier.finances.gestion.communs.parametrages.model.enums.IdsCategoriesEnum;

public class TestBudgetMensuel {

	
	@Test
	public void testMarge(){
		
		BudgetMensuel b = new BudgetMensuel();
		
		assertEquals(0, b.getMarge().intValue());
		
		b.setResultatMoisPrecedent(100D, 200D);
		assertEquals(200, b.getMarge().intValue());
		
		LigneOperation o = new LigneOperation();
		o.setSsCategorie(new CategorieOperation(IdsCategoriesEnum.SALAIRE));
		o.setValeurAbsStringToDouble("123");
		o.setTypeDepense(TypeOperationEnum.CREDIT);
		b.getListeOperations().add(o);
		
		assertEquals(200, b.getMarge().intValue());
		
		LigneOperation o2 = new LigneOperation();
		o2.setSsCategorie(new CategorieOperation(IdsCategoriesEnum.RESERVE));
		o2.setValeurAbsStringToDouble("123");
		o2.setTypeDepense(TypeOperationEnum.CREDIT);
		b.getListeOperations().add(o2);
		assertEquals(323, b.getMarge().intValue());
	}
}
