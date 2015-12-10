package com.conflux.mifosplatform.client.domain;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.apache.commons.lang.StringUtils;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Where;
import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepository;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.serialization.JsonParserHelper;
import org.mifosplatform.organisation.office.domain.Office;
import org.mifosplatform.organisation.staff.domain.Staff;
import org.mifosplatform.portfolio.client.api.ClientApiConstants;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientStatus;
import org.mifosplatform.portfolio.group.domain.Group;
import org.mifosplatform.portfolio.loanaccount.api.LoanApiConstants;
import org.mifosplatform.portfolio.loanaccount.domain.LoanCharge;
import org.mifosplatform.portfolio.loanaccount.exception.MultiDisbursementDataRequiredException;
import org.mifosplatform.portfolio.savings.domain.SavingsAccount;
import org.mifosplatform.portfolio.savings.domain.SavingsProduct;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.data.jpa.domain.AbstractPersistable;

import com.conflux.mifosplatform.client.api.ClientExtensionAPIConstants;
import com.conflux.mifosplatform.common.Address;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

@Entity
@Table(name = "ct_client_extension")
public class ClientExtension extends AbstractPersistable<Long> {

	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;

	@Column(name = "mother_maiden_name", nullable = true, length = 50)
	private String motherMaidenName;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy="clientExtension", orphanRemoval = true)
	@Where(clause = "reference_type = 1")
	private Set<Address> addresses;

	@ManyToOne
	@JoinColumn(name = "updated_by", nullable = true)
	private AppUser updatedBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updated_on", nullable = false)
	private Date updatedOn;

	public ClientExtension(AppUser currentUser, Client client,
			String motherMaidenName,
			Set<Address> addresses) {
		this.motherMaidenName = motherMaidenName;
		this.updatedBy = currentUser;
		this.client = client;
		this.addresses = addresses;
		this.updatedOn = new Date();
	}

	public static ClientExtension createNew(final AppUser currentUser, Client client,
			CodeValueRepositoryWrapper codeValueRepository,
			final JsonCommand command) {

		final String motherMaidenName = command
				.stringValueOfParameterNamed(ClientExtensionAPIConstants.motherMaidenNameParamName);
		
		final JsonArray addressDataArray = command.arrayOfParameterNamed(ClientExtensionAPIConstants.addressesParamName);

		Set<Address> addresses = new HashSet<>();
		ClientExtension clientExt = new ClientExtension (currentUser, client, motherMaidenName, addresses);
		
        if (addressDataArray != null && addressDataArray.size() > 0) {
        	for (JsonElement jsonElement : addressDataArray) {
        		final JsonObject jsonObject = jsonElement.getAsJsonObject();
        		Map<String, Object> parsedAddressData = parseAddressData(jsonObject);
        		
        		Integer addressType = (Integer) parsedAddressData.get(ClientExtensionAPIConstants.addressTypeParamName);
        		CodeValue addressTypeCodeValue = codeValueRepository.findOneByCodeNameAndIdWithNotFoundDetection(
        				ClientExtensionAPIConstants.ADDRESS_TYPE_CODE, addressType.longValue());
        		
                String doorNoWithBuildingName = (String) parsedAddressData.get(ClientExtensionAPIConstants.doorNoBuildingNameParamName);
                String streetAddress1 = (String) parsedAddressData.get(ClientExtensionAPIConstants.streetAddress1ParamName);
                String address2 = (String) parsedAddressData.get(ClientExtensionAPIConstants.address2ParamName);
                String city = (String) parsedAddressData.get(ClientExtensionAPIConstants.cityParamName);
                String postalCode = (String) parsedAddressData.get(ClientExtensionAPIConstants.postalCodeParamName);
                
                Integer state = (Integer) parsedAddressData.get(ClientExtensionAPIConstants.stateParamName);
                CodeValue stateCodeValue = codeValueRepository.findOneByCodeNameAndIdWithNotFoundDetection(
                		ClientExtensionAPIConstants.STATE_CODE, state.longValue());
                Integer country = (Integer) parsedAddressData.get(ClientExtensionAPIConstants.countryParamName);
                CodeValue countryCodeValue = codeValueRepository.findOneByCodeNameAndIdWithNotFoundDetection(
                		ClientExtensionAPIConstants.COUNTRY_CODE, country.longValue());
                
                String landmark = (String) parsedAddressData.get(ClientExtensionAPIConstants.landmarkParamName);
                
                Date updatedOn = new Date();
                
                Address address = new Address(addressTypeCodeValue,
                		ClientExtensionAPIConstants.AddressTypeForClient, 
                		// client.getId(), 
                		doorNoWithBuildingName, streetAddress1, address2, city, postalCode,
                		stateCodeValue, countryCodeValue, currentUser, updatedOn, landmark);
                
                // addresses.add(address);
                clientExt.addAddress(address);
        	}
        }
		return clientExt;
	}
	
	public static void update (final AppUser currentUser, ClientExtension clientExt,
			CodeValueRepositoryWrapper codeValueRepository,
			final JsonCommand command) {
		
		final String motherMaidenName = command
				.stringValueOfParameterNamed(ClientExtensionAPIConstants.motherMaidenNameParamName);
		
		final JsonArray addressDataArray = command.arrayOfParameterNamed(ClientExtensionAPIConstants.addressesParamName);

        if (addressDataArray != null && addressDataArray.size() > 0) {
        	if (clientExt.addresses != null) {
        		clientExt.addresses.clear();
        	}
        	
        	for (JsonElement jsonElement : addressDataArray) {
        		final JsonObject jsonObject = jsonElement.getAsJsonObject();
        		Map<String, Object> parsedAddressData = parseAddressData(jsonObject);
        		
        		Integer addressType = (Integer) parsedAddressData.get(ClientExtensionAPIConstants.addressTypeParamName);
        		CodeValue addressTypeCodeValue = codeValueRepository.findOneByCodeNameAndIdWithNotFoundDetection(
        				ClientExtensionAPIConstants.ADDRESS_TYPE_CODE, addressType.longValue());
        		
                String doorNoWithBuildingName = (String) parsedAddressData.get(ClientExtensionAPIConstants.doorNoBuildingNameParamName);
                String streetAddress1 = (String) parsedAddressData.get(ClientExtensionAPIConstants.streetAddress1ParamName);
                String address2 = (String) parsedAddressData.get(ClientExtensionAPIConstants.address2ParamName);
                String city = (String) parsedAddressData.get(ClientExtensionAPIConstants.cityParamName);
                String postalCode = (String) parsedAddressData.get(ClientExtensionAPIConstants.postalCodeParamName);
                
                Integer state = (Integer) parsedAddressData.get(ClientExtensionAPIConstants.stateParamName);
                CodeValue stateCodeValue = codeValueRepository.findOneByCodeNameAndIdWithNotFoundDetection(
                		ClientExtensionAPIConstants.STATE_CODE, state.longValue());
                Integer country = (Integer) parsedAddressData.get(ClientExtensionAPIConstants.countryParamName);
                CodeValue countryCodeValue = codeValueRepository.findOneByCodeNameAndIdWithNotFoundDetection(
                		ClientExtensionAPIConstants.COUNTRY_CODE, country.longValue());
                
                String landmark = (String) parsedAddressData.get(ClientExtensionAPIConstants.landmarkParamName);
                
                Date updatedOn = new Date();
                
                Address address = new Address(addressTypeCodeValue,
                		ClientExtensionAPIConstants.AddressTypeForClient,
                		// clientExt.client.getId(), 
                		doorNoWithBuildingName, streetAddress1, address2, city, postalCode,
                		stateCodeValue, countryCodeValue, currentUser, updatedOn, landmark);
            
                clientExt.addAddress(address);
        	}
        }
        clientExt.motherMaidenName = motherMaidenName;
        
		// return clientExt;
	}

	
	private static Map<String, Object> parseAddressData(final JsonObject jsonObject) {
        Map<String, Object> returnObject = new HashMap<>();
        
        if (jsonObject.get(ClientExtensionAPIConstants.addressTypeParamName) != null
                && jsonObject.get(ClientExtensionAPIConstants.addressTypeParamName).isJsonPrimitive()) {
            final JsonPrimitive primitive = jsonObject.get(ClientExtensionAPIConstants.addressTypeParamName).getAsJsonPrimitive();
            final Integer valueAsInt = primitive.getAsInt();
            returnObject.put(ClientExtensionAPIConstants.addressTypeParamName, valueAsInt);
        }
        
        if (jsonObject.get(ClientExtensionAPIConstants.doorNoBuildingNameParamName) != null
                && jsonObject.get(ClientExtensionAPIConstants.doorNoBuildingNameParamName).isJsonPrimitive()) {
            final JsonPrimitive primitive = jsonObject.get(ClientExtensionAPIConstants.doorNoBuildingNameParamName).getAsJsonPrimitive();
            final String valueAsString = primitive.getAsString();
            returnObject.put(ClientExtensionAPIConstants.doorNoBuildingNameParamName, valueAsString);
        }
        
        if (jsonObject.get(ClientExtensionAPIConstants.streetAddress1ParamName) != null
                && jsonObject.get(ClientExtensionAPIConstants.streetAddress1ParamName).isJsonPrimitive()) {
            final JsonPrimitive primitive = jsonObject.get(ClientExtensionAPIConstants.streetAddress1ParamName).getAsJsonPrimitive();
            final String valueAsString = primitive.getAsString();
            returnObject.put(ClientExtensionAPIConstants.streetAddress1ParamName, valueAsString);
        }
        
        if (jsonObject.get(ClientExtensionAPIConstants.address2ParamName) != null
                && jsonObject.get(ClientExtensionAPIConstants.address2ParamName).isJsonPrimitive()) {
            final JsonPrimitive primitive = jsonObject.get(ClientExtensionAPIConstants.address2ParamName).getAsJsonPrimitive();
            final String valueAsString = primitive.getAsString();
            returnObject.put(ClientExtensionAPIConstants.address2ParamName, valueAsString);
        }
        
        if (jsonObject.get(ClientExtensionAPIConstants.cityParamName) != null
                && jsonObject.get(ClientExtensionAPIConstants.cityParamName).isJsonPrimitive()) {
            final JsonPrimitive primitive = jsonObject.get(ClientExtensionAPIConstants.cityParamName).getAsJsonPrimitive();
            final String valueAsString = primitive.getAsString();
            returnObject.put(ClientExtensionAPIConstants.cityParamName, valueAsString);
        }
        
        if (jsonObject.get(ClientExtensionAPIConstants.postalCodeParamName) != null
                && jsonObject.get(ClientExtensionAPIConstants.postalCodeParamName).isJsonPrimitive()) {
            final JsonPrimitive primitive = jsonObject.get(ClientExtensionAPIConstants.postalCodeParamName).getAsJsonPrimitive();
            final String valueAsString = primitive.getAsString();
            returnObject.put(ClientExtensionAPIConstants.postalCodeParamName, valueAsString);
        }
        
        if (jsonObject.get(ClientExtensionAPIConstants.stateParamName) != null
                && jsonObject.get(ClientExtensionAPIConstants.stateParamName).isJsonPrimitive()) {
            final JsonPrimitive primitive = jsonObject.get(ClientExtensionAPIConstants.stateParamName).getAsJsonPrimitive();
            final Integer valueAsInt = primitive.getAsInt();
            returnObject.put(ClientExtensionAPIConstants.stateParamName, valueAsInt);
        }
        
        if (jsonObject.get(ClientExtensionAPIConstants.countryParamName) != null
                && jsonObject.get(ClientExtensionAPIConstants.countryParamName).isJsonPrimitive()) {
            final JsonPrimitive primitive = jsonObject.get(ClientExtensionAPIConstants.countryParamName).getAsJsonPrimitive();
            final Integer valueAsInt = primitive.getAsInt();
            returnObject.put(ClientExtensionAPIConstants.countryParamName, valueAsInt);
        }
        
        if (jsonObject.get(ClientExtensionAPIConstants.landmarkParamName) != null
                && jsonObject.get(ClientExtensionAPIConstants.landmarkParamName).isJsonPrimitive()) {
            final JsonPrimitive primitive = jsonObject.get(ClientExtensionAPIConstants.landmarkParamName).getAsJsonPrimitive();
            final String valueAsString = primitive.getAsString();
            returnObject.put(ClientExtensionAPIConstants.landmarkParamName, valueAsString);
        }
        
        return returnObject;
    }
	
	public void addAddress (Address address) {
		if (this.addresses == null) {
			addresses = new HashSet<>();
		}
		address.setClientExtension(this);
		this.addresses.add(address);
	}
	
	protected ClientExtension () {
        this.client = null;
        this.addresses = null;
    }

}
