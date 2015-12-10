
package com.conflux.mifosplatform.portfolio.client.data;

import java.math.BigDecimal;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;

/**
 * Immutable data object representing client relationships data.
 */
final public class ClientCashFlowDetailsData {
    private final Long id;
    private final CodeValueData cashflowType;
    private final CodeValueData cashflowSubType;
    private BigDecimal cashflowAmount;
    
    public static ClientCashFlowDetailsData instance(
    		final Long id,
    		final CodeValueData cashflowType,
    	    final CodeValueData cashflowSubType,
    	    final BigDecimal cashflowAmount
    		) {

        return new ClientCashFlowDetailsData(id, cashflowType, cashflowSubType, 
        		cashflowAmount
        		);
    }

    private ClientCashFlowDetailsData(
    		final Long id,
    		final CodeValueData cashflowType,
    	    final CodeValueData cashflowSubType,
    	    final BigDecimal cashflowAmount
		) {
	    this.id = id;
	    this.cashflowType = cashflowType;
	    this.cashflowSubType = cashflowSubType;
	    this.cashflowAmount = cashflowAmount;
    }

    public Long id() {
        return this.id;
    }

    public CodeValueData cashflowType() {
        return this.cashflowType;
    }

    public CodeValueData cashflowSubType() {
        return this.cashflowSubType;
    }
    public BigDecimal cashflowAmount() {
        return this.cashflowAmount;
    }
    
}