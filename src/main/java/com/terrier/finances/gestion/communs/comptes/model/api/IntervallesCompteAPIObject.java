package com.terrier.finances.gestion.communs.comptes.model.api;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.terrier.finances.gestion.communs.abstrait.AbstractAPIObjectModel;
import com.terrier.finances.gestion.communs.utils.data.DataUtils;

import io.swagger.annotations.ApiModelProperty;

public class IntervallesCompteAPIObject extends AbstractAPIObjectModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2380780514003066552L;

	
	@ApiModelProperty(notes = "Date du premier budget du compte", required=true)
	private Long datePremierBudget;
	
	@ApiModelProperty(notes = "Date du dernier budget du compte", required=true)
	private Long dateDernierBudget;

	/**
	 * @return the datePremierBudget
	 */
	public Long getDatePremierBudget() {
		return datePremierBudget;
	}

	/**
	 * @param datePremierBudget the datePremierBudget to set
	 */
	public void setDatePremierBudget(Long datePremierBudget) {
		this.datePremierBudget = datePremierBudget;
	}

	/**
	 * @return the dateDernierBudget
	 */
	public Long getDateDernierBudget() {
		return dateDernierBudget;
	}

	/**
	 * @param dateDernierBudget the dateDernierBudget to set
	 */
	public void setDateDernierBudget(Long dateDernierBudget) {
		this.dateDernierBudget = dateDernierBudget;
	}
	

	/**
	 * @return the datePremierBudget
	 */
	@JsonIgnore
	public LocalDate getLocalDatePremierBudget() {
		return DataUtils.getLocalDateFromLong(datePremierBudget);
	}
	/**
	 * @return the dateDernierBudget
	 */
	@JsonIgnore
	public LocalDate getLocalDateDernierBudget() {
		return DataUtils.getLocalDateFromLong(dateDernierBudget);
	}

	
}
