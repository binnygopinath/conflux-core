package com.conflux.mifosplatform.portfolio.client.service;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.mifosplatform.infrastructure.codes.data.CodeValueData;
import org.mifosplatform.infrastructure.codes.service.CodeValueReadPlatformService;
import org.mifosplatform.infrastructure.core.domain.JdbcSupport;
import org.mifosplatform.infrastructure.core.service.RoutingDataSource;
import org.mifosplatform.infrastructure.core.service.SearchParameters;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.data.ClientData;
import org.mifosplatform.portfolio.client.exception.ClientNotFoundException;
import org.mifosplatform.portfolio.client.service.ClientReadPlatformService;
import org.mifosplatform.useradministration.domain.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.conflux.mifosplatform.client.api.ClientExtensionAPIConstants;
import com.conflux.mifosplatform.common.AddressData;
import com.conflux.mifosplatform.portfolio.client.data.ClientExtensionData;

@Service
public class ClientExtensionReadPlatformServiceImpl implements
		ClientExtensionReadPlatformService {
	
	private final JdbcTemplate jdbcTemplate;
    private final PlatformSecurityContext context;
    private final CodeValueReadPlatformService codeValueReadPlatformService;
    private final ClientReadPlatformService clientReadPlatformService;
    
    private final ClientExtensionMapper clientExtensionMapper = new ClientExtensionMapper();
    private final ClientAddressMapper clientAddressMapper = new ClientAddressMapper();
	
	@Autowired
    public ClientExtensionReadPlatformServiceImpl (
    		final PlatformSecurityContext context,
    		final RoutingDataSource dataSource,
            final CodeValueReadPlatformService codeValueReadPlatformService,
            final ClientReadPlatformService clientReadPlatformService) {
        this.context = context;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.codeValueReadPlatformService = codeValueReadPlatformService;
        this.clientReadPlatformService = clientReadPlatformService;
    }

	@Override
	public ClientExtensionData retrieveTemplate(ClientData client) {

		this.context.authenticatedUser();

        final List<CodeValueData> allowedMaritalStatusOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.MARITAL_STATUS_CODE));
        final List<CodeValueData> allowedParentTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.PARENT_TYPES_CODE));
        final List<CodeValueData> allowedRelationshipTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.RELATIONSHIP_TYPES_CODE));
        final List<CodeValueData> allowedCountryOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.COUNTRY_CODE));
        final List<CodeValueData> allowedStateOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.STATE_CODE));
        final List<CodeValueData> allowedBankAccountTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.BANK_ACCOUNT_TYPES_CODE));
        
        final List<CodeValueData> allowedClientIDDocTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.CLIENT_ID_TYPE));
        final List<CodeValueData> allowedAddressTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.ADDRESS_TYPE_CODE));
        
        final List<CodeValueData> allowedCashflowTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.CASHFLOW_TYPES_CODE));
        final List<CodeValueData> allowedAssetTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.ASSETS_TYPES_CODE));
        final List<CodeValueData> allowedLiabilityTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.LIABILITIES_TYPES_CODE));
        final List<CodeValueData> allowedIncomeTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.INCOME_TYPES_CODE));
        final List<CodeValueData> allowedExpenseTypeOptions = new ArrayList<>(
                this.codeValueReadPlatformService.retrieveCodeValuesByCode(
                		ClientExtensionAPIConstants.EXPENSE_TYPES_CODE));

        return ClientExtensionData.template(
        		client,
        		allowedMaritalStatusOptions,
        		allowedParentTypeOptions,
        		allowedClientIDDocTypeOptions,
        		allowedRelationshipTypeOptions,
        		allowedCashflowTypeOptions,
        		allowedAssetTypeOptions,
        		allowedLiabilityTypeOptions,
        		allowedIncomeTypeOptions,
        		allowedExpenseTypeOptions,
        		allowedBankAccountTypeOptions,
        		allowedAddressTypeOptions,
        		allowedStateOptions,
        		allowedCountryOptions
        		);

	}

	@Override
	public ClientExtensionData retrieveOne(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ClientExtensionData retrieveOneByClient(Long clientId) {
		try {
            final String clientExtSql = "select " + this.clientExtensionMapper.schema()
                    + " where ce.client_id = ?";
            final ClientExtensionData clientExtensionData = this.jdbcTemplate.queryForObject(
            		clientExtSql, this.clientExtensionMapper, new Object[] {clientId });

            if (clientExtensionData != null) {
            	final Collection<AddressData> addresses = retrieveClientAddresses(clientId);
            	clientExtensionData.setAddresses(addresses);
            }

            return clientExtensionData;

        } catch (final EmptyResultDataAccessException e) {
        	// Do nothing, because it is ok to have a client who does not have any "extended" data
            // throw new ClientNotFoundException(clientId);
        	return null;
        }
	}
	
	@Override
    public Collection<AddressData> retrieveClientAddresses(final Long clientId) {
		
        final String clientAddrSql = "select " + this.clientAddressMapper.schema()
                + " where ce.client_id = ?";
        return this.jdbcTemplate.query(clientAddrSql, this.clientAddressMapper, 
        		new Object[] { clientId });
    }
	
	private static final class ClientExtensionMapper implements RowMapper<ClientExtensionData> {

        private final String schema;

        public ClientExtensionMapper() {
            final StringBuilder builder = new StringBuilder(400);

            builder.append("ce.id id, ce.client_id client_id, ce.mother_maiden_name mother_maiden_name, ");
			builder.append("ce.updated_by updated_by, ce.updated_on updated_on ");
			builder.append("from ct_client_extension ce ");

            this.schema = builder.toString();
        }

        public String schema() {
            return this.schema;
        }

        @Override
        public ClientExtensionData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {

            final Long id = rs.getLong("id");
			// final Long client_id = rs.getLong("client_id");
			final String motherMaidenName = rs.getString("mother_maiden_name");
			final Long updatedByUserId = rs.getLong("updated_by");
			final Date updatedOn = rs.getDate ("updated_on");

            return ClientExtensionData.instance(id, null, motherMaidenName, updatedByUserId, new LocalDate(updatedOn));

        }

    }
	
	private static final class ClientAddressMapper implements RowMapper<AddressData> {

        private final String schema;

        public ClientAddressMapper() {
            final StringBuilder builder = new StringBuilder(400);

			builder.append("a.id addr_id, a.reference_type addr_reference_type, ");
			builder.append("a.reference_id addr_reference_id, a.address_type_cv_id address_type_code, ");
			builder.append("a.door_no_building_name door_no_building_name, a.street_address1 street_address1, ");
			builder.append("a.address2 address2, a.city city, a.postal_code postal_code, ");
			builder.append("a.state_cv_id state_code, a.country_cv_id country_code, ");
			builder.append("a.updated_by addr_updated_by, a.updated_on addr_updated_on, a.landmark landmark, ");
			builder.append("cvaddrtype.code_value addr_type_name, cvaddrtype.id addr_type_id, ");
			builder.append("cvstate.code_value state_name, cvstate.id state_id, ");
			builder.append("cvcountry.code_value country_name, cvcountry.id country_id ");
			builder.append("from ct_client_extension ce ");
			builder.append("left outer join ct_address a on ce.id = a.reference_id and a.reference_type = 1 ");
			builder.append("left outer join m_code_value cvaddrtype on a.address_type_cv_id = cvaddrtype.id ");
			builder.append("left outer join m_code_value cvstate on a.state_cv_id = cvstate.id ");
			builder.append("left outer join m_code_value cvcountry on a.country_cv_id = cvcountry.id ");

            this.schema = builder.toString();
        }

        public String schema() {
            return this.schema;
        }

        @Override
        public AddressData mapRow(final ResultSet rs, @SuppressWarnings("unused") final int rowNum) throws SQLException {
            final Long addrID = JdbcSupport.getLong(rs, "addr_id");
            final Integer addrReferenceType = rs.getInt("addr_reference_type");
			final Long addrReferenceID = JdbcSupport.getLong(rs, "addr_reference_id");
            final String doorNoBuildingName = rs.getString("door_no_building_name");
			final String streetAddress1 = rs.getString("street_address1");
			final String address2 = rs.getString("address2");
			final String city = rs.getString("city");
			final String postalCode = rs.getString("postal_code");
			final String landmark = rs.getString("landmark");

            final Long addrTypeId = JdbcSupport.getLong(rs, "addr_type_id");
            final String addrTypeName = rs.getString("addr_type_name");
            final CodeValueData addrType = CodeValueData.instance(addrTypeId, addrTypeName);
			
			final Long stateId = JdbcSupport.getLong(rs, "state_id");
            final String stateName = rs.getString("state_name");
            final CodeValueData state = CodeValueData.instance(stateId, stateName);
			
			final Long countryId = JdbcSupport.getLong(rs, "country_id");
            final String countryName = rs.getString("country_name");
            final CodeValueData country = CodeValueData.instance(countryId, countryName);

            return AddressData.instance(addrID, addrType,
            		addrReferenceType, addrReferenceID,
            		doorNoBuildingName, streetAddress1, address2, city,
            		postalCode, state, country, null, null, landmark
            		);
        }
    }

}
