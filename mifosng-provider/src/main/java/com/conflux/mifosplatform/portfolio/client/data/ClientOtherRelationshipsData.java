package com.conflux.mifosplatform.portfolio.client.data;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;

/**
 * Immutable data object representing client relationships data.
 */
final public class ClientOtherRelationshipsData {
    private final Long id;
    private final CodeValueData relationshipType;
    private final String firstName;
    private final String lastName;
    private final String middleName;
    private final CodeValueData idType;
    private final String idValue;
    private LocalDate DOB;

    public static ClientOtherRelationshipsData instance(
    		final Long id,
    	    final CodeValueData relationshipType,
    	    final String firstName,
    	    final String lastName,
    	    final String middleName,
    	    final CodeValueData idType,
    	    final String idValue,
    	    LocalDate DOB
    		) {

        return new ClientOtherRelationshipsData(id, relationshipType,
        		firstName, lastName, middleName,
        		idType, idValue, DOB);
    }

    private ClientOtherRelationshipsData(
		final Long id,
	    final CodeValueData relationshipType,
	    final String firstName,
	    final String lastName,
	    final String middleName,
	    final CodeValueData idType,
	    final String idValue,
	    LocalDate DOB
		) {
	    this.id = id;
	    this.relationshipType = relationshipType;
	    this.firstName = firstName;
	    this.lastName = lastName;
	    this.middleName = middleName;
	    this.idType = idType;
	    this.idValue = idValue;
	    this.DOB = DOB;
    }

    public Long id() {
        return this.id;
    }

    public CodeValueData relationshipType() {
        return this.relationshipType;
    }

    public String firstName() {
        return this.firstName;
    }

    public String lastName() {
        return this.lastName;
    }
    
    public String middleName() {
        return this.middleName;
    }
    
    public CodeValueData idType() {
        return this.idType;
    }

    public String idValue() {
        return this.idValue;
    }

    public LocalDate DOB() {
        return this.DOB;
    }
    
}