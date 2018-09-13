package com.terrier.finances.gestion.model.data;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;

import org.junit.Test;

import com.terrier.finances.gestion.communs.utils.data.BudgetDateTimeUtils;

/**
 * @author vzwingma
 *
 */
public class TestBudgetDateTimeUtils {

	
	@Test
	public void testDates(){
		
		LocalDate now = Instant.now().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate dataUtilsNow = BudgetDateTimeUtils.localDateNow();
		
		assertEquals(now, dataUtilsNow);
		
		assertEquals(1, BudgetDateTimeUtils.localDateFirstDayOfMonth().getDayOfMonth());
		assertEquals(now.getMonth(), BudgetDateTimeUtils.localDateFirstDayOfMonth().getMonth());
	}
	
	
	
	
	@Test
	public void testDateLibelle(){
		String libelle = BudgetDateTimeUtils.getLibelleDate(LocalDateTime.now());
		assertNotNull(libelle);
	}
	
	@Test
	public void testLocalDateTime(){
		LocalDateTime t = LocalDateTime.now();
		t = t.minus(t.getNano(), ChronoUnit.NANOS);
		Long lt = BudgetDateTimeUtils.getLongFromLocalDateTime(t);
		assertNotNull(lt);
		LocalDateTime dt = BudgetDateTimeUtils.getLocalDateTimeFromLong(lt);
		assertNotNull(dt);
		assertEquals(t, dt);
	}
	

	@Test
	public void testLocalDate(){
		LocalDate t = LocalDate.now();
		Long lt = BudgetDateTimeUtils.getLongFromLocalDate(t);
		assertNotNull(lt);
		LocalDate dt = BudgetDateTimeUtils.getLocalDateFromLong(lt);
		assertNotNull(dt);
		assertEquals(t, dt);
	}
}
