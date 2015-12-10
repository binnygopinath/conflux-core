package com.conflux.mifosplatform.common;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Where;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.conflux.mifosplatform.client.domain.ClientExtension;

@Entity
@Table (name="ct_address")
public class Address extends AbstractPersistable<Long> {
	
	@Column(name = "reference_type", nullable = false)
	private int referenceType;
		
	// @Column(name = "reference_id", nullable = false, length = 20)
	// private Long referenceId;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_type_cv_id", nullable = false)
    private CodeValue addressType;

	@Column(name = "door_no_building_name", nullable = true, length = 100)
	private String doorNoBuildingName;
	
	@Column(name = "street_address1", nullable = true, length = 100)
	private String streetAddress1;
	
	@Column(name = "address2", nullable = true, length = 100)
	private String address2;
	
	@Column(name = "city", nullable = true, length = 60)
	private String city;
	
	@Column(name = "postal_code", nullable = true, length = 10)
	private String postalCode;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "state_cv_id", nullable = false)
    private CodeValue state;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_cv_id", nullable = false)
    private CodeValue country;

    @ManyToOne
    @JoinColumn(name = "updated_by", nullable = true)
    private AppUser updatedBy;
    
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name = "updated_on", nullable = false)
    private Date updatedOn;
	
	@Column(name = "landmark", nullable = true, length = 10)
	private String landmark;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumns({
        @JoinColumn(name="reference_id", nullable = false)
    })
	@Where (clause = "reference_type = 1")
	private ClientExtension clientExtension;
	
	public Address (CodeValue addressType, int referenceType, 
			// Long referenceId,
			String doorNoBuildingName, String streetAddress1,
			String address2, String city, String postalCode, CodeValue state,
			CodeValue country, AppUser updatedBy, Date updatedOn, String landmark) {
		this.referenceType = referenceType;
		// this.referenceId = referenceId;
		this.addressType = addressType;
		this.doorNoBuildingName = doorNoBuildingName;
		this.streetAddress1 = streetAddress1;
		this.address2 = address2;
		this.city = city;
		this.postalCode = postalCode;
		this.state = state;
		this.country = country;
		this.updatedBy = updatedBy;
		this.updatedOn = updatedOn;
		this.landmark = landmark;
		// this.clientExtension = clientExtension;
	}
	
	public void setClientExtension (ClientExtension clientExtension) {
		this.clientExtension = clientExtension;
		// this.referenceId = clientExtension.getId();
		System.out.println ("Setting Reference ID to:" + clientExtension.getId());
	}
	
	protected Address () {
	} 

}
