package com.conflux.mifosplatform.portfolio.client.data;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;

/**
 * Immutable data object representing client relationships data.
 */
final public class ClientMainRelationshipsData {

    private final Long id;
    private final CodeValueData maritalStatus;
    private final CodeValueData parentType;
    private final String parentFirstName;
    private final String parentLastName;
    private final String parentMiddleName;
    private final CodeValueData parentIDType;
    private final String parentIDValue;
    private LocalDate parentDOB;
    private final String spouseFirstName;
    private final String spouseLastName;
    private final String spouseMiddleName;
    private final CodeValueData spouseIDType;
    private final String spouseIDValue;
    private LocalDate spouseDOB;

    public static ClientMainRelationshipsData instance(
    		final Long id,
    	    final CodeValueData maritalStatus,
    	    final CodeValueData parentType,
    	    final String parentFirstName,
    	    final String parentLastName,
    	    final String parentMiddleName,
    	    final CodeValueData parentIDType,
    	    final String parentIDValue,
    	    LocalDate parentDOB,
    	    final String spouseFirstName,
    	    final String spouseLastName,
    	    final String spouseMiddleName,
    	    final CodeValueData spouseIDType,
    	    final String spouseIDValue,
    	    LocalDate spouseDOB
    		) {

        return new ClientMainRelationshipsData(id, maritalStatus, parentType,
        		parentFirstName, parentLastName, parentMiddleName,
        		parentIDType, parentIDValue, parentDOB,
        		spouseFirstName, spouseLastName, spouseMiddleName,
        		spouseIDType, spouseIDValue, spouseDOB);
    }

    private ClientMainRelationshipsData(
		final Long id,
	    final CodeValueData maritalStatus,
	    final CodeValueData parentType,
	    final String parentFirstName,
	    final String parentLastName,
	    final String parentMiddleName,
	    final CodeValueData parentIDType,
	    final String parentIDValue,
	    LocalDate parentDOB,
	    final String spouseFirstName,
	    final String spouseLastName,
	    final String spouseMiddleName,
	    final CodeValueData spouseIDType,
	    final String spouseIDValue,
	    LocalDate spouseDOB
		) {
	    this.id = id;
	    this.maritalStatus = maritalStatus;
	    this.parentType = parentType;
	    this.parentFirstName = parentFirstName;
	    this.parentLastName = parentLastName;
	    this.parentMiddleName = parentMiddleName;
	    this.parentIDType = parentIDType;
	    this.parentIDValue = parentIDValue;
	    this.parentDOB = parentDOB;
	    this.spouseFirstName = spouseFirstName;
	    this.spouseLastName = spouseLastName;
	    this.spouseMiddleName = spouseMiddleName;
	    this.spouseIDType = spouseIDType;
	    this.spouseIDValue = spouseIDValue;
	    this.spouseDOB = spouseDOB;	
    }

    public Long id() {
        return this.id;
    }

    public CodeValueData maritalStatus() {
        return this.maritalStatus;
    }

    public CodeValueData parentType() {
        return this.parentType;
    }

    public String parentFirstName() {
        return this.parentFirstName;
    }

    public String parentLastName() {
        return this.parentLastName;
    }
    
    public String parentMiddleName() {
        return this.parentMiddleName;
    }
    
    public CodeValueData parentIDType() {
        return this.parentIDType;
    }

    public String parentIDValue() {
        return this.parentIDValue;
    }

    public LocalDate parentDOB() {
        return this.parentDOB;
    }
    
    public String spouseFirstName() {
        return this.spouseFirstName;
    }

    public String spouseLastName() {
        return this.spouseLastName;
    }
    
    public String spouseMiddleName() {
        return this.spouseMiddleName;
    }
    
    public CodeValueData spouseIDType() {
        return this.spouseIDType;
    }

    public String spouseIDValue() {
        return this.spouseIDValue;
    }

    public LocalDate spouseDOB() {
        return this.spouseDOB;
    }

}