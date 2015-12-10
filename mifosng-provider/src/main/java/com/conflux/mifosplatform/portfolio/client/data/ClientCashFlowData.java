
package com.conflux.mifosplatform.portfolio.client.data;

import java.util.Collection;

import org.joda.time.LocalDateTime;
import org.mifosplatform.useradministration.domain.AppUser;

/**
 * Immutable data object representing client relationships data.
 */
final public class ClientCashFlowData {
    private final Long id;
    private final String comments;
    private final AppUser updatedBy;
    private final LocalDateTime updatedOn;
    
    private final Collection<ClientCashFlowDetailsData> incomeDetails;
    private final Collection<ClientCashFlowDetailsData> expenseDetails;
    private final Collection<ClientCashFlowDetailsData> assetDetails;
    private final Collection<ClientCashFlowDetailsData> liabilityDetails;
    
    public static ClientCashFlowData instance(
    		final Long id,
    		final Collection<ClientCashFlowDetailsData> incomeDetails,
    		final Collection<ClientCashFlowDetailsData> expenseDetails,
    		final Collection<ClientCashFlowDetailsData> assetDetails,
    		final Collection<ClientCashFlowDetailsData> liabilityDetails,
    		final String comments,
    		final AppUser updatedBy,
    		final LocalDateTime updatedOn
    		) {

        return new ClientCashFlowData(id, incomeDetails, expenseDetails,
        		assetDetails, liabilityDetails, comments, updatedBy, updatedOn
        		);
    }

    private ClientCashFlowData(
    		final Long id,
    		Collection<ClientCashFlowDetailsData> incomeDetails,
    		Collection<ClientCashFlowDetailsData> expenseDetails,
    		Collection<ClientCashFlowDetailsData> assetDetails,
    		Collection<ClientCashFlowDetailsData> liabilityDetails,
    		final String comments,
    		final AppUser updatedBy,
    		final LocalDateTime updatedOn
		) {
	    this.id = id;
	    this.incomeDetails = incomeDetails;
	    this.expenseDetails = expenseDetails;
	    this.assetDetails = assetDetails;
	    this.liabilityDetails = liabilityDetails;
	    this.comments = comments;
	    this.updatedBy = updatedBy;
	    this.updatedOn = updatedOn;
    }

    public Long id() {
        return this.id;
    }

    public Collection<ClientCashFlowDetailsData> incomeDetails() {
        return this.incomeDetails;
    }
    
    public Collection<ClientCashFlowDetailsData> expenseDetails() {
        return this.expenseDetails;
    }

    public Collection<ClientCashFlowDetailsData> assetDetails() {
        return this.assetDetails;
    }

    public Collection<ClientCashFlowDetailsData> liabilityDetails() {
        return this.liabilityDetails;
    }

    public String comments() {
        return this.comments;
    }
    
    public AppUser updatedBy() {
        return this.updatedBy;
    }

    public LocalDateTime updatedOn() {
        return this.updatedOn;
    }
    
}