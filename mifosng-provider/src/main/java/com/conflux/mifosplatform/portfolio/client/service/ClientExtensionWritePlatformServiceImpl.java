package com.conflux.mifosplatform.portfolio.client.service;

import java.util.Locale;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.mifosplatform.commands.service.CommandProcessingService;
import org.mifosplatform.infrastructure.codes.domain.CodeValueRepositoryWrapper;
import org.mifosplatform.infrastructure.configuration.domain.ConfigurationDomainService;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResultBuilder;
import org.mifosplatform.infrastructure.core.exception.PlatformDataIntegrityException;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.domain.Client;
import org.mifosplatform.portfolio.client.domain.ClientRepository;
import org.mifosplatform.portfolio.client.exception.ClientNotFoundException;
import org.mifosplatform.portfolio.client.service.ClientWritePlatformServiceJpaRepositoryImpl;
import org.mifosplatform.useradministration.domain.AppUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conflux.mifosplatform.client.domain.ClientExtension;
import com.conflux.mifosplatform.client.domain.ClientExtensionRepository;
import com.conflux.mifosplatform.portfolio.client.data.ClientExtensionDataValidator;

@Service
public class ClientExtensionWritePlatformServiceImpl implements
		ClientExtensionWritePlatformService {
	
	private final static Logger logger = LoggerFactory.getLogger(ClientWritePlatformServiceJpaRepositoryImpl.class);

    private final PlatformSecurityContext context;
    private final ClientExtensionRepository clientExtRepository;
    private final ClientExtensionDataValidator fromApiJsonDeserializer;
    private final CodeValueRepositoryWrapper codeValueRepository;
    private final CommandProcessingService commandProcessingService;
    private final ConfigurationDomainService configurationDomainService;
    private final ClientRepository clientRepository;

    @Autowired
    public ClientExtensionWritePlatformServiceImpl(
    		final PlatformSecurityContext context,
            final ClientExtensionRepository clientExtRepository,
            final ClientExtensionDataValidator fromApiJsonDeserializer,
            final CodeValueRepositoryWrapper codeValueRepository, 
            final CommandProcessingService commandProcessingService, 
            final ConfigurationDomainService configurationDomainService,
            final ClientRepository clientRepository) {
        this.context = context;
        this.fromApiJsonDeserializer = fromApiJsonDeserializer;
        this.clientExtRepository = clientExtRepository;
        this.codeValueRepository = codeValueRepository;
        this.commandProcessingService = commandProcessingService;
        this.configurationDomainService = configurationDomainService;
        this.clientRepository = clientRepository;
    }

	@Transactional
	@Override
	public CommandProcessingResult createClientExtension (Long clientId,
			JsonCommand command) {
        try {
            final AppUser currentUser = this.context.authenticatedUser();

            this.fromApiJsonDeserializer.validateForCreate(command.json());
            
            final Client client = this.clientRepository.findOne(clientId);
            if (client == null) { throw new ClientNotFoundException(clientId); }
            

            final ClientExtension newClientExtension = ClientExtension.createNew(currentUser, client,
            		this.codeValueRepository,
            		command);

            this.clientExtRepository.save(newClientExtension);

            // final Locale locale = command.extractLocale();
            // final DateTimeFormatter fmt = DateTimeFormat.forPattern(command.dateFormat()).withLocale(locale);

            return new CommandProcessingResultBuilder() //
                    .withCommandId(command.commandId()) //
                    .withEntityId(newClientExtension.getId()) //
                    .build();
        } catch (final DataIntegrityViolationException dve) {
            handleDataIntegrityIssues(command, dve);
            return CommandProcessingResult.empty();
        }
	}

	@Override
	public CommandProcessingResult updateClientExtension(Long clientId, 
			Long extId, JsonCommand command) {
		
        final AppUser currentUser = this.context.authenticatedUser();

        this.fromApiJsonDeserializer.validateForUpdate (command.json());
        
        final ClientExtension clientExt = this.clientExtRepository.findOne(extId);
        if (clientExt == null) { throw new ClientNotFoundException(extId); }
        

        ClientExtension.update (currentUser, 
        		clientExt,
        		this.codeValueRepository,
        		command); 

        this.clientExtRepository.saveAndFlush(clientExt);

        // final Locale locale = command.extractLocale();
        // final DateTimeFormatter fmt = DateTimeFormat.forPattern(command.dateFormat()).withLocale(locale);

        return new CommandProcessingResultBuilder() //
                .withCommandId(command.commandId()) //
                .withClientId(clientId) //
                .withEntityId(extId)
                .build();
	}

	@Override
	public CommandProcessingResult deleteClientExtension(Long clientId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/*
     * Guaranteed to throw an exception no matter what the data integrity issue
     * is.
     */
    private void handleDataIntegrityIssues(final JsonCommand command, final DataIntegrityViolationException dve) {

        final Throwable realCause = dve.getMostSpecificCause();
        if (realCause.getMessage().contains("external_id")) {

            final String externalId = command.stringValueOfParameterNamed("externalId");
            throw new PlatformDataIntegrityException("error.msg.client.duplicate.externalId", "Client with externalId `" + externalId
                    + "` already exists", "externalId", externalId);
        } else if (realCause.getMessage().contains("account_no_UNIQUE")) {
            final String accountNo = command.stringValueOfParameterNamed("accountNo");
            throw new PlatformDataIntegrityException("error.msg.client.duplicate.accountNo", "Client with accountNo `" + accountNo
                    + "` already exists", "accountNo", accountNo);
        } else if (realCause.getMessage().contains("mobile_no")) {
            final String mobileNo = command.stringValueOfParameterNamed("mobileNo");
            throw new PlatformDataIntegrityException("error.msg.client.duplicate.mobileNo", "Client with mobileNo `" + mobileNo
                    + "` already exists", "mobileNo", mobileNo);
        }

        logger.error(dve.getMessage(), dve);
        
        throw new PlatformDataIntegrityException("error.msg.client.unknown.data.integrity.issue",
                "Unknown data integrity issue with resource.");
    }

}
