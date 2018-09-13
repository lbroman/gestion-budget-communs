package com.terrier.finances.gestion.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.Test;

import com.terrier.finances.gestion.communs.comptes.model.CompteBancaire;
import com.terrier.finances.gestion.communs.operations.model.LigneOperation;
import com.terrier.finances.gestion.communs.utils.data.BudgetDataUtils;

public class TestBudgetDataUtils {

	@Test
	public void testDoubleFromString(){
		assertNull(BudgetDataUtils.getValueFromString(null));
		assertNull(BudgetDataUtils.getValueFromString("123/0"));
		assertEquals("123.3", BudgetDataUtils.getValueFromString("123.3"));
		assertEquals("123.3", BudgetDataUtils.getValueFromString("123,3"));
		assertEquals("-123.3", BudgetDataUtils.getValueFromString("-123,3"));
	}
	

	@Test
	public void testMaxDateOperations(){
		
		Calendar c = Calendar.getInstance();
		LigneOperation depense1 = new LigneOperation();
		depense1.setDateOperation(c.getTime());
		LigneOperation depense2 = new LigneOperation();
		c.set(Calendar.DAY_OF_MONTH, 31);
		depense2.setDateOperation(c.getTime());
		
		LigneOperation depense3 = new LigneOperation();
		Calendar c3 = Calendar.getInstance();
		c3.set(Calendar.DAY_OF_MONTH, 12);
		c3.set(Calendar.MONTH, Calendar.OCTOBER);
		c3.set(Calendar.YEAR, 2050);
		depense3.setDateOperation(c3.getTime());
		
		List<LigneOperation> depenses = new ArrayList<>();
		depenses.addAll(Arrays.asList(depense1, depense2, depense3));
		LocalDate cd = BudgetDataUtils.getMaxDateListeOperations(depenses);
		
		assertEquals(Month.OCTOBER.getValue(), cd.get(ChronoField.MONTH_OF_YEAR));
	}
	
	
	@Test
	public void getBudgetId(){
		CompteBancaire c1 = new CompteBancaire();
		c1.setId("ING");
		assertEquals("ING_2018_1", BudgetDataUtils.getBudgetId(c1, Month.JANUARY, 2018));
	}
	
	
	@Test
	public void getAnneeFromBudgetId(){
		assertEquals("2018", BudgetDataUtils.getAnneeFromBudgetId("ING_2018_1"));
	}
}
