/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.conflux.mifosplatform.portfolio.client.data;

import java.io.Serializable;
import java.util.Collection;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.portfolio.client.data.ClientData;

import com.conflux.mifosplatform.common.AddressData;

/**
 * Immutable data object representing client data.
 */
final public class ClientExtensionData implements Serializable {

    private final Long id;
    private final String motherMaidenName;
    private final Long updatedByUserId;
    private final LocalDate updatedOn;
    
    private ClientData client;
    private Collection<AddressData> clientAddresses;
    private final ClientMainRelationshipsData clientMainRelationships;
    private final ClientOtherRelationshipsData clientOtherRelationships;
    private final ClientCashFlowData clientCashFlow;
    private final ClientHouseVisitData clientHouseVisit;

    // template
    private final Collection<CodeValueData> allowedMaritalStatusOptions;
    private final Collection<CodeValueData> allowedParentTypeOptions;
    private final Collection<CodeValueData> allowedDocumentTypes;
    private final Collection<CodeValueData> allowedRelationshipTypeOptions;
    private final Collection<CodeValueData> allowedCashflowTypeOptions;
    private final Collection<CodeValueData> allowedAssetTypeOptions;
    private final Collection<CodeValueData> allowedLiabilityTypeOptions;
    private final Collection<CodeValueData> allowedIncomeTypeOptions;
    private final Collection<CodeValueData> allowedExpenseTypeOptions;
    private final Collection<CodeValueData> allowedAccountTypeOptions;
    private final Collection<CodeValueData> allowedCountryOptions;
    private final Collection<CodeValueData> allowedStateOptions;
    private final Collection<CodeValueData> allowedAddressTypeOptions;

    public static ClientExtensionData template(
    		final ClientData client,
    		final Collection<CodeValueData> allowedMaritalStatusOptions,
    		final Collection<CodeValueData> allowedParentTypeOptions,
    		final Collection<CodeValueData> allowedDocumentTypes,
    		final Collection<CodeValueData> allowedRelationshipTypeOptions,
    		final Collection<CodeValueData> allowedCashflowTypeOptions,
    		final Collection<CodeValueData> allowedAssetTypeOptions,
    	    final Collection<CodeValueData> allowedLiabilityTypeOptions,
    	    final Collection<CodeValueData> allowedIncomeTypeOptions,
    	    final Collection<CodeValueData> allowedExpenseTypeOptions,
    		final Collection<CodeValueData> allowedAccountTypeOptions,
    		final Collection<CodeValueData> allowedAddressTypeOptions,
    		final Collection<CodeValueData> allowedStateOptions,
    		final Collection<CodeValueData> allowedCountryOptions
    		) {
        return new ClientExtensionData(null,
        		null, null, null,
        		client, null, 
            	null, null, null, null, 
            	allowedMaritalStatusOptions, allowedParentTypeOptions,
            	allowedDocumentTypes, allowedRelationshipTypeOptions,
            	allowedCashflowTypeOptions, allowedAssetTypeOptions,
            	allowedLiabilityTypeOptions, allowedIncomeTypeOptions, allowedExpenseTypeOptions,
            	allowedAccountTypeOptions, allowedAddressTypeOptions,
            	allowedStateOptions, allowedCountryOptions
        	);

    }

    public static ClientExtensionData templateOnTop(final ClientExtensionData clientExtData, final ClientExtensionData templateData) {

        return new ClientExtensionData(clientExtData.id(),
        		clientExtData.motherMaidenName(),
        		clientExtData.updatedByUserId(),
        		clientExtData.updatedOn(),
        		clientExtData.client(), 
        		clientExtData.clientAddresses(), clientExtData.clientMainRelationships(),
        		clientExtData.clientOtherRelationships(), clientExtData.clientCashFlow(),
        		clientExtData.clientHouseVisit(),
        		templateData.allowedMaritalStatusOptions(),
        		templateData.allowedParentTypeOptions(),
        		templateData.allowedDocumentTypes(),
        		templateData.allowedRelationshipTypeOptions(),
        		templateData.allowedCashflowTypeOptions(), 
        		templateData.allowedAssetTypeOptions(),
        		templateData.allowedLiabilityTypeOptions(),
        		templateData.allowedIncomeTypeOptions(),
        		templateData.allowedExpenseTypeOptions(),
        		templateData.allowedBankAccountTypeOptions(),
        		templateData.allowedAddressTypeOptions,
        		templateData.allowedStateOptions,
        		templateData.allowedCountryOptions);

    }
    
    public static ClientExtensionData instance(final Long id,
    		final ClientData client,
    		final String motherMaidenName,
    		final Long updatedByUserId,
    		final LocalDate updatedOn) {

        final Collection<CodeValueData> allowedMaritalStatusOptions = null;
        final Collection<CodeValueData> allowedParentTypeOptions = null;
        final Collection<CodeValueData> allowedDocumentTypes = null;
        final Collection<CodeValueData> allowedRelationshipTypeOptions = null;
        final Collection<CodeValueData> allowedCashflowTypeOptions = null;
        final Collection<CodeValueData> allowedAccountTypeOptions = null;
        final Collection<CodeValueData> allowedAddressTypeOptions = null;
        final Collection<CodeValueData> allowedStateOptions = null;
        final Collection<CodeValueData> allowedCountryOptions = null;
        final Collection<CodeValueData> allowedAssetTypeOptions = null;
        final Collection<CodeValueData> allowedLiabilityTypeOptions = null;
        final Collection<CodeValueData> allowedIncomeTypeOptions = null;
        final Collection<CodeValueData> allowedExpenseTypeOptions = null;
        
        
        return new ClientExtensionData(id, motherMaidenName, updatedByUserId, updatedOn,
        	client, null, 
        	null, null, null,
        	null, allowedMaritalStatusOptions, allowedParentTypeOptions,
        	allowedDocumentTypes, allowedRelationshipTypeOptions,
        	allowedCashflowTypeOptions, allowedAssetTypeOptions ,
        	allowedLiabilityTypeOptions, allowedIncomeTypeOptions, allowedExpenseTypeOptions,
        	allowedAccountTypeOptions, allowedAddressTypeOptions,
        	allowedStateOptions, allowedCountryOptions);
    }

    public static ClientExtensionData instance(final Long id,
    		final ClientData client,
    		final String motherMaidenName,
    		final Long updatedByUserId,
    		final LocalDate updatedOn,
    		final Collection<AddressData> clientAddresses,
    		final ClientMainRelationshipsData clientMainRelationships,
    		final ClientOtherRelationshipsData clientOtherRelationships,
    		final ClientCashFlowData clientCashFlow,
    		final ClientHouseVisitData clientHouseVisit) {

        final Collection<CodeValueData> allowedMaritalStatusOptions = null;
        final Collection<CodeValueData> allowedParentTypeOptions = null;
        final Collection<CodeValueData> allowedDocumentTypes = null;
        final Collection<CodeValueData> allowedRelationshipTypeOptions = null;
        final Collection<CodeValueData> allowedCashflowTypeOptions = null;
        final Collection<CodeValueData> allowedAccountTypeOptions = null;
        final Collection<CodeValueData> allowedAddressTypeOptions = null;
        final Collection<CodeValueData> allowedStateOptions = null;
        final Collection<CodeValueData> allowedCountryOptions = null;
        final Collection<CodeValueData> allowedAssetTypeOptions = null;
        final Collection<CodeValueData> allowedLiabilityTypeOptions = null;
        final Collection<CodeValueData> allowedIncomeTypeOptions = null;
        final Collection<CodeValueData> allowedExpenseTypeOptions = null;
        
        
        return new ClientExtensionData(id, motherMaidenName, updatedByUserId, updatedOn,
        	client, clientAddresses, 
        	clientMainRelationships, clientOtherRelationships, clientCashFlow,
        	clientHouseVisit, allowedMaritalStatusOptions, allowedParentTypeOptions,
        	allowedDocumentTypes, allowedRelationshipTypeOptions,
        	allowedCashflowTypeOptions, allowedAssetTypeOptions ,
        	allowedLiabilityTypeOptions, allowedIncomeTypeOptions, allowedExpenseTypeOptions,
        	allowedAccountTypeOptions, allowedAddressTypeOptions,
        	allowedStateOptions, allowedCountryOptions);
    }

    private ClientExtensionData(
		final Long id,
		final String motherMaidenName,
		final Long updatedByUserId,
		final LocalDate updatedOn,
		final ClientData client,
		final Collection<AddressData> clientAddresses,
		final ClientMainRelationshipsData clientMainRelationships,
		final ClientOtherRelationshipsData clientOtherRelationships,
		final ClientCashFlowData clientCashFlow,
		final ClientHouseVisitData clientHouseVisit,
		final Collection<CodeValueData> allowedMaritalStatusOptions,
		final Collection<CodeValueData> allowedParentTypeOptions,
		final Collection<CodeValueData> allowedDocumentTypes,
		final Collection<CodeValueData> allowedRelationshipTypeOptions,
		final Collection<CodeValueData> allowedCashflowTypeOptions,
		final Collection<CodeValueData> allowedAssetTypeOptions,
	    final Collection<CodeValueData> allowedLiabilityTypeOptions,
	    final Collection<CodeValueData> allowedIncomeTypeOptions,
	    final Collection<CodeValueData> allowedExpenseTypeOptions,
		final Collection<CodeValueData> allowedAccountTypeOptions,
		final Collection<CodeValueData> allowedAddressTypeOptions,
		final Collection<CodeValueData> allowedStateOptions,
		final Collection<CodeValueData> allowedCountryOptions
		) {
    	this.id = id;
		this.motherMaidenName = motherMaidenName;
		this.updatedByUserId = updatedByUserId;
		this.updatedOn = updatedOn;
    	this.client = client;
    	this.clientAddresses = clientAddresses;
    	this.clientMainRelationships = clientMainRelationships;
    	this.clientOtherRelationships = clientOtherRelationships;
    	this.clientCashFlow = clientCashFlow;
    	this.clientHouseVisit = clientHouseVisit;
    	this.allowedMaritalStatusOptions = allowedMaritalStatusOptions;
    	this.allowedParentTypeOptions = allowedParentTypeOptions;
    	this.allowedDocumentTypes = allowedDocumentTypes;
    	this.allowedRelationshipTypeOptions = allowedRelationshipTypeOptions;
    	this.allowedCashflowTypeOptions = allowedCashflowTypeOptions;
    	this.allowedAssetTypeOptions = allowedAssetTypeOptions;
    	this.allowedLiabilityTypeOptions = allowedLiabilityTypeOptions;
    	this.allowedIncomeTypeOptions = allowedIncomeTypeOptions;
    	this.allowedExpenseTypeOptions = allowedExpenseTypeOptions;
    	this.allowedAccountTypeOptions = allowedAccountTypeOptions;
    	this.allowedCountryOptions = allowedCountryOptions;
    	this.allowedStateOptions = allowedStateOptions;
    	this.allowedAddressTypeOptions = allowedAddressTypeOptions;
    }

    public Long id() {
        return this.id;
    }
    
    public String motherMaidenName() {
    	return this.motherMaidenName;
    }
    
	public Long updatedByUserId() {
		return this.updatedByUserId;
	}
	
	public LocalDate updatedOn() {
		return this.updatedOn;
	}

    public ClientData client() {
        return this.client;
    }

    public Collection<AddressData> clientAddresses() {
        return this.clientAddresses;
    }

    public ClientMainRelationshipsData clientMainRelationships() {
        return this.clientMainRelationships;
    }

    public ClientOtherRelationshipsData clientOtherRelationships() {
        return this.clientOtherRelationships;
    }

    public ClientCashFlowData clientCashFlow() {
        return this.clientCashFlow;
    }

    public ClientHouseVisitData clientHouseVisit() {
        return this.clientHouseVisit;
    }

    public Collection<CodeValueData> allowedMaritalStatusOptions() {
        return this.allowedMaritalStatusOptions;
    }

    public Collection<CodeValueData> allowedParentTypeOptions() {
        return this.allowedParentTypeOptions;
    }
    
    public Collection<CodeValueData> allowedDocumentTypes() {
        return this.allowedDocumentTypes;
    }
    
    public Collection<CodeValueData> allowedRelationshipTypeOptions() {
        return this.allowedRelationshipTypeOptions;
    }
    
    public Collection<CodeValueData> allowedCashflowTypeOptions() {
        return this.allowedCashflowTypeOptions;
    }
    
    public Collection<CodeValueData> allowedBankAccountTypeOptions() {
        return this.allowedAccountTypeOptions;
    }
    
    public Collection<CodeValueData> allowedAssetTypeOptions() {
        return this.allowedAssetTypeOptions;
    }
    
    public Collection<CodeValueData> allowedLiabilityTypeOptions() {
        return this.allowedLiabilityTypeOptions;
    }
    
    public Collection<CodeValueData> allowedIncomeTypeOptions() {
        return this.allowedIncomeTypeOptions;
    }
    
    public Collection<CodeValueData> allowedExpenseTypeOptions() {
        return this.allowedExpenseTypeOptions;
    }

    public void setClient (ClientData client) {
    	this.client = client;
    }

    public void setAddresses (Collection<AddressData> clientAddresses) {
    	this.clientAddresses = clientAddresses;
    }
}