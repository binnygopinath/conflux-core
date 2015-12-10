package com.conflux.mifosplatform.portfolio.client.service;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;

public interface ClientExtensionWritePlatformService {

	CommandProcessingResult createClientExtension (Long clientId, JsonCommand command);

    CommandProcessingResult updateClientExtension (Long id, Long clientId, JsonCommand command);

    CommandProcessingResult deleteClientExtension (Long clientId);

}