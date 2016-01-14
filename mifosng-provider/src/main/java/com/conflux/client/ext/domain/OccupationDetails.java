/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.conflux.client.ext.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.portfolio.client.domain.Client;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "ct_cfaoccupations")
public class OccupationDetails extends AbstractPersistable<Long> {

	@ManyToOne(optional = false)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "occupation_type_cv_id", nullable = false)
	private CodeValue occupationType;

	@Column(name = "annual_revenue")
	private BigDecimal annulaRevenue;

	@Column(name = "annual_expense")
	private BigDecimal annualExpense;

	@Column(name = "annual_surplus")
	private BigDecimal annualSurplus;


	public static OccupationDetails createFrom(final Client client,
			final CodeValue occupationTypeId, final BigDecimal annulaRevenue,
			final BigDecimal annual_expense, final BigDecimal annualSurplus) {

		return new OccupationDetails(client, occupationTypeId, annulaRevenue,
				annual_expense, annualSurplus);
	}

	protected OccupationDetails() {
		//
	}

	private OccupationDetails(final Client client,
			final CodeValue occupationTypeId, final BigDecimal annulaRevenue,
			final BigDecimal annualExpense, final BigDecimal annualSurplus) {

		this.client = client;
		this.occupationType = occupationTypeId;
		this.annulaRevenue = annulaRevenue;
		this.annualExpense = annualExpense;
		this.annualSurplus = annualSurplus;
	}
	
	public void update(final Client client, final CodeValue occupationType,final BigDecimal annulaRevenue,
			final BigDecimal annualExpense, final BigDecimal annualSurplus) {
		
		this.annulaRevenue = annulaRevenue;
		this.annualExpense = annualExpense;
		this.annualSurplus = annualSurplus;
		this.occupationType = occupationType;
		this.client = client;
		
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public CodeValue getOccupationType() {
		return occupationType;
	}

	public void setOccupationType(CodeValue occupationType) {
		this.occupationType = occupationType;
	}

	public BigDecimal getAnnulaRevenue() {
		return annulaRevenue;
	}

	public void setAnnulaRevenue(BigDecimal annulaRevenue) {
		this.annulaRevenue = annulaRevenue;
	}

	public BigDecimal getAnnualExpense() {
		return annualExpense;
	}

	public void setAnnualExpense(BigDecimal annualExpense) {
		this.annualExpense = annualExpense;
	}

	public BigDecimal getAnnualSurplus() {
		return annualSurplus;
	}

	public void setAnnualSurplus(BigDecimal annualSurplus) {
		this.annualSurplus = annualSurplus;
	}

	public void updateClient(final Client client) {
		this.client = client;
	}

}
