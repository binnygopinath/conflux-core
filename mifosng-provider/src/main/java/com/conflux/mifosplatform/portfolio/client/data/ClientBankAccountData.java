package com.conflux.mifosplatform.portfolio.client.data;

import org.mifosplatform.infrastructure.codes.data.CodeValueData;

/**
 * Immutable data object representing client relationships data.
 */
final public class ClientBankAccountData {
    private final Long id;
    private final CodeValueData accountType;
    private final String bankName;
    private final String bankBranch;
    private final String accountName;
    private final String accountNumber;
    private final String ifscCode;

    public static ClientBankAccountData instance(
    		final Long id,
    	    final CodeValueData accountType,
    	    final String bankName,
    	    final String bankBranch,
    	    final String accountName,
    	    final String accountNumber,
    	    final String ifscCode
    		) {

        return new ClientBankAccountData(id, accountType,
        		bankName, bankBranch, accountName, accountNumber, ifscCode);
    }

    private ClientBankAccountData(
		final Long id,
	    final CodeValueData accountType,
	    final String bankName,
	    final String bankBranch,
	    final String accountName,
	    final String accountNumber,
	    final String ifscCode
		) {
	    this.id = id;
	    this.accountType = accountType;
	    this.bankName = bankName;
	    this.bankBranch = bankBranch;
	    this.accountName = accountName;
	    this.accountNumber = accountNumber;
	    this.ifscCode = ifscCode;
    }

    public Long id() {
        return this.id;
    }

    public CodeValueData accountType() {
        return this.accountType;
    }

    public String bankName() {
        return this.bankName;
    }

    public String bankBranch() {
        return this.bankBranch;
    }
    
    public String accountName() {
        return this.accountName;
    }
    
    public String accountNumber() {
        return this.accountNumber;
    }

    public String ifscCode() {
        return this.ifscCode;
    }
    
}