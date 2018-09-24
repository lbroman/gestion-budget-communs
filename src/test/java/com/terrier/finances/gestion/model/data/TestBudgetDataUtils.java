package com.terrier.finances.gestion.model.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.time.LocalDate;
import java.time.Month;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.terrier.finances.gestion.communs.comptes.model.CompteBancaire;
import com.terrier.finances.gestion.communs.operations.model.LigneOperation;
import com.terrier.finances.gestion.communs.parametrages.model.CategorieOperation;
import com.terrier.finances.gestion.communs.utils.data.BudgetDataUtils;
import com.terrier.finances.gestion.communs.utils.exceptions.BudgetNotFoundException;

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
	public void getAnneeFromBudgetId() throws BudgetNotFoundException{
		String id1 = "ING_2018_1";
		
		assertEquals(Integer.valueOf(2018), BudgetDataUtils.getAnneeFromBudgetId(id1));
		assertEquals(Month.JANUARY, BudgetDataUtils.getMoisFromBudgetId(id1));
		assertEquals("ING", BudgetDataUtils.getCompteFromBudgetId(id1));
		
		String id2 = "ingdirectV_2018_8";

		assertEquals(Integer.valueOf(2018), BudgetDataUtils.getAnneeFromBudgetId(id2));
		assertEquals(Month.AUGUST, BudgetDataUtils.getMoisFromBudgetId(id2));
		assertEquals("ingdirectV", BudgetDataUtils.getCompteFromBudgetId(id2));
	}
	

	
	@Test
	public void testGetCategorie(){
		
		
		List<CategorieOperation> categoriesFromDB = new ArrayList<>();
		CategorieOperation catAlimentation = new CategorieOperation();
		catAlimentation.setId("8f1614c9-503c-4e7d-8cb5-0c9a9218b84a");
		catAlimentation.setActif(true);
		catAlimentation.setCategorie(true);
		catAlimentation.setLibelle("Alimentation");


		CategorieOperation ssCatCourse = new CategorieOperation();
		ssCatCourse.setActif(true);
		ssCatCourse.setCategorie(false);
		ssCatCourse.setId("467496e4-9059-4b9b-8773-21f230c8c5c6");
		ssCatCourse.setLibelle("Courses");
		ssCatCourse.setCategorieParente(catAlimentation);
		catAlimentation.getListeSSCategories().add(ssCatCourse);
		categoriesFromDB.add(catAlimentation);
		
		
		CategorieOperation cat = BudgetDataUtils.getCategorieById("8f1614c9-503c-4e7d-8cb5-0c9a9218b84a", categoriesFromDB);
		assertNotNull(cat);
		
		CategorieOperation ssCat = BudgetDataUtils.getCategorieById("467496e4-9059-4b9b-8773-21f230c8c5c6", categoriesFromDB);
		assertNotNull(ssCat);
		assertNotNull(ssCat.getCategorieParente());
		assertEquals("8f1614c9-503c-4e7d-8cb5-0c9a9218b84a", ssCat.getCategorieParente().getId());
	}
	
	
	

	@Test
	public void testGetCategorieById(){
		
		List<CategorieOperation> categoriesFromDB = new ArrayList<>();
		
		for (int i = 0; i < 9; i++) {
			
			CategorieOperation cat = new CategorieOperation();
			cat.setId("ID" + i);
			cat.setActif(true);
			cat.setCategorie(true);
			cat.setLibelle("CAT" + i);
			
			for (int j = 0; j < 9; j++) {
				CategorieOperation ssCat = new CategorieOperation();
				ssCat.setActif(true);
				ssCat.setCategorie(false);
				ssCat.setId("ID" + i + j);
				ssCat.setLibelle("SSCAT" + j);
				ssCat.setCategorieParente(cat);
				cat.getListeSSCategories().add(ssCat);
				
				
			}
			categoriesFromDB.add(cat);
		}

		CategorieOperation cat = BudgetDataUtils.getCategorieById("ID8", categoriesFromDB);
		assertNotNull(cat);
		
		CategorieOperation ssCat = BudgetDataUtils.getCategorieById("ID88", categoriesFromDB);
		assertNotNull(ssCat);
		assertNotNull(ssCat.getCategorieParente());
		assertEquals("ID8", ssCat.getCategorieParente().getId());
	}
}
