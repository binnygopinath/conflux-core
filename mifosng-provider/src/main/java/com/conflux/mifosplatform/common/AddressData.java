
package com.conflux.mifosplatform.common;

import org.joda.time.LocalDateTime;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.useradministration.domain.AppUser;

/**
 * Immutable data object representing client relationships data.
 */
final public class AddressData {
    private final Long id;
    private final CodeValueData addressType;
    private final Integer referenceType;
    private final Long referenceId;
    private final String doorNoWithBuildingName;
    private final String streetAddress1;
    private final String address2;
    private final String city;
    private final String postalCode;
    private final CodeValueData state;
    private final CodeValueData country;
    private final AppUser updatedBy;
    private final LocalDateTime updatedOn;
    private final String landmark;
    
    public static AddressData instance(
    		final Long id,
    		final CodeValueData addressType,
    		final Integer referenceType,
    		final Long referenceId,
    		final String doorNoWithBuildingName,
    		final String streetAddress1,
    		final String address2,
    		final String city,
    		final String postalCode,
    		final CodeValueData state,
    		final CodeValueData country,
    		final AppUser updatedBy,
    		final LocalDateTime updatedOn,
    		final String landmark
    		) {

        return new AddressData(id, addressType, referenceType, referenceId,
        		doorNoWithBuildingName, streetAddress1, address2, city, postalCode,
        		state, country,updatedBy, updatedOn, landmark
        		);
    }

    private AddressData(
    		final Long id,
    		final CodeValueData addressType,
    		final Integer referenceType,
    		final Long referenceId,
    		final String doorNoWithBuildingName,
    		final String streetAddress1,
    		final String address2,
    		final String city,
    		final String postalCode,
    		final CodeValueData state,
    		final CodeValueData country,
    		final AppUser updatedBy,
    		final LocalDateTime updatedOn,
    		final String landmark
		) {
	    this.id = id;
	    this.addressType = addressType;
	    this.referenceId = referenceId;
	    this.referenceType = referenceType;
	    this.doorNoWithBuildingName = doorNoWithBuildingName;
	    this.streetAddress1 = streetAddress1;
	    this.address2 = address2;
	    this.city = city;
	    this.postalCode = postalCode;
	    this.state = state;
	    this.country = country;
	    this.updatedBy = updatedBy;
	    this.updatedOn = updatedOn;
	    this.landmark = landmark;
    }

    public Long id() {
        return this.id;
    }

    public CodeValueData addressType() {
        return this.addressType;
    }
    
    public Integer referenceType() {
    	return this.referenceType;
    }
    
    public Long referenceId() {
    	return this.referenceId;
    }

    public String doorNoWithBuildingName() {
        return this.doorNoWithBuildingName;
    }
    
    public String streetAddress1() {
        return this.streetAddress1;
    }
    
    public String address2() {
        return this.address2;
    }
    
    public String city() {
        return this.city;
    }

    public String postalCode() {
        return this.postalCode;
    }

    public CodeValueData state() {
        return this.state;
    }
    
    public CodeValueData country() {
        return this.country;
    }
    
    public AppUser updatedBy() {
        return this.updatedBy;
    }

    public LocalDateTime updatedOn() {
        return this.updatedOn;
    }
    
    public String landmark() {
        return this.landmark;
    }
}