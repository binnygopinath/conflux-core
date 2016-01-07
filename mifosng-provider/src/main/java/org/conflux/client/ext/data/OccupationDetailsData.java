package org.conflux.client.ext.data;

import java.math.BigDecimal;

import org.conflux.client.ext.domain.OccupationDetails;

public class OccupationDetailsData {

	private final Long id;
	private final Long occupationTypeId;
	private final String occupationTypeLabel;
	private final BigDecimal annualRevenue;
	private final BigDecimal annualExpense;
	private final BigDecimal annualSurplus;
	
public static OccupationDetailsData formOccupationDetailsData(final OccupationDetails occupationDetails) {
		
		Long id = occupationDetails.getId();
		BigDecimal annualRevenue = occupationDetails.getAnnulaRevenue();
		BigDecimal annualExpense = occupationDetails.getAnnualExpense();
		BigDecimal annualSurplus = occupationDetails.getAnnualSurplus();

		Long occupationTypeId = null;
		String occupationTypeLabel = null;
		if(occupationDetails.getOccupationType() != null){
			occupationTypeId = occupationDetails.getOccupationType().getId();
			occupationTypeLabel = occupationDetails.getOccupationType().label();
		}
		
		return new OccupationDetailsData(id, occupationTypeId, annualRevenue, annualExpense, annualSurplus, occupationTypeLabel);
	}
	
	private OccupationDetailsData(final Long id, final Long occupationTypeId, 
			final BigDecimal annualRevenue, final BigDecimal annualExpense,	final BigDecimal annualSurplus, final String occupationTypeLabel) {
		
		this.id = id;
		this.occupationTypeId = occupationTypeId;
		this.occupationTypeLabel = occupationTypeLabel;
		this.annualRevenue = annualRevenue;
		this.annualExpense = annualExpense;
		this.annualSurplus = annualSurplus;
	}

	public Long getId() {
		return id;
	}

	public BigDecimal getAnnualRevenue() {
		return annualRevenue;
	}

	public BigDecimal getAnnualExpense() {
		return annualExpense;
	}

	public BigDecimal getAnnualSurplus() {
		return annualSurplus;
	}

	public Long getOccupationTypeId() {
		return occupationTypeId;
	}

	public String getOccupationTypeLabel() {
		return occupationTypeLabel;
	}
	
	
	
}
