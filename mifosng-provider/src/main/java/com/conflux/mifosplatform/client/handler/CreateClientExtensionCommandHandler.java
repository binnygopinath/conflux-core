/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.conflux.mifosplatform.client.handler;

import org.mifosplatform.commands.annotation.CommandType;
import org.mifosplatform.commands.handler.NewCommandSourceHandler;
import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.portfolio.client.service.ClientWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.conflux.mifosplatform.portfolio.client.service.ClientExtensionWritePlatformService;

@Service
@CommandType(entity = "CLIENT", action = "CREATEEXT")
public class CreateClientExtensionCommandHandler implements NewCommandSourceHandler {

    private final ClientExtensionWritePlatformService clientExtWritePlatformService;

    @Autowired
    public CreateClientExtensionCommandHandler(final ClientExtensionWritePlatformService clientExtWritePlatformService) {
        this.clientExtWritePlatformService = clientExtWritePlatformService;
    }

    @Transactional
    @Override
    public CommandProcessingResult processCommand(final JsonCommand command) {

        return this.clientExtWritePlatformService.createClientExtension(command.getClientId(), command);
    }
}
