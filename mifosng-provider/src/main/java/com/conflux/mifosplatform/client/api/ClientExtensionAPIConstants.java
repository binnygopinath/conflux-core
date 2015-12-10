package com.conflux.mifosplatform.client.api;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ClientExtensionAPIConstants {
	
	public static final String CLIENT_RESOURCE_NAME = "client";
	
	// request parameters
	public static final String idParamName = "id";
	public static final String motherMaidenNameParamName = "motherMaidenName";
	public static final String addressesParamName = "addresses";
	public static final String addressTypeParamName = "addressType";
	public static final String doorNoBuildingNameParamName = "doorNoBuildingName";
	public static final String streetAddress1ParamName = "streetAddress1";
	public static final String address2ParamName = "address2";
	public static final String cityParamName = "city";
	public static final String postalCodeParamName = "postalCode";
	public static final String stateParamName = "state";
	public static final String countryParamName = "country";
	public static final String landmarkParamName = "landmark";
	
	// Others
	public static final String ADDRESS_TYPE_CODE = "Address Types";
	public static final String COUNTRY_CODE = "Country";
	public static final String STATE_CODE = "State";
	public static final String MARITAL_STATUS_CODE = "Marital Status";
	public static final String PARENT_TYPES_CODE = "Parent Types";
	public static final String RELATIONSHIP_TYPES_CODE = "Relationship Types";
	public static final String BANK_ACCOUNT_TYPES_CODE = "Bank Account Types";
	
	public static final String CASHFLOW_TYPES_CODE = "Cash Flow Types";
	public static final String ASSETS_TYPES_CODE = "Assets Types";
	public static final String LIABILITIES_TYPES_CODE = "Liabilities Types";
	public static final String INCOME_TYPES_CODE = "Income Types";
	public static final String EXPENSE_TYPES_CODE = "Expense Types";
	public static final String CLIENT_ID_TYPE = "Customer Identifier";
	
	public static final int AddressTypeForClient = 1;
	
	public static final Set<String> CLIENT_CREATE_REQUEST_DATA_PARAMETERS = new HashSet<>(
		Arrays.asList(motherMaidenNameParamName, addressesParamName, addressTypeParamName,
    		addressTypeParamName, doorNoBuildingNameParamName, streetAddress1ParamName,
    		address2ParamName, cityParamName, postalCodeParamName, stateParamName,
    		countryParamName, landmarkParamName));

    public static final Set<String> CLIENT_UPDATE_REQUEST_DATA_PARAMETERS = new HashSet<>
    	(Arrays.asList(
    		motherMaidenNameParamName, addressesParamName, addressTypeParamName,
    		addressTypeParamName, doorNoBuildingNameParamName, streetAddress1ParamName,
    		address2ParamName, cityParamName, postalCodeParamName, stateParamName,
    		countryParamName, landmarkParamName
    		));
    
    public static final Set<String> CLIENT_RESPONSE_DATA_PARAMETERS = new HashSet<>(Arrays.asList(
    		idParamName, motherMaidenNameParamName, addressesParamName, addressTypeParamName, 
    		doorNoBuildingNameParamName, streetAddress1ParamName, address2ParamName, 
    		cityParamName, postalCodeParamName, stateParamName, countryParamName, landmarkParamName));

}
