/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.conflux.mifosplatform.client.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

import org.mifosplatform.commands.domain.CommandWrapper;
import org.mifosplatform.commands.service.CommandWrapperBuilder;
import org.mifosplatform.commands.service.PortfolioCommandSourceWritePlatformService;
import org.mifosplatform.infrastructure.core.api.ApiRequestParameterHelper;
import org.mifosplatform.infrastructure.core.data.CommandProcessingResult;
import org.mifosplatform.infrastructure.core.serialization.ApiRequestJsonSerializationSettings;
import org.mifosplatform.infrastructure.core.serialization.ToApiJsonSerializer;
import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.accountdetails.data.AccountSummaryCollectionData;
import org.mifosplatform.portfolio.accountdetails.service.AccountDetailsReadPlatformService;
import org.mifosplatform.portfolio.client.api.ClientsApiResource;
import org.mifosplatform.portfolio.client.data.ClientData;
import org.mifosplatform.portfolio.client.service.ClientReadPlatformService;
import org.mifosplatform.portfolio.savings.service.SavingsAccountReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.conflux.mifosplatform.portfolio.client.data.ClientExtensionData;
import com.conflux.mifosplatform.portfolio.client.service.ClientExtensionReadPlatformService;

@Path("/clientsext")
@Component
@Scope("singleton")
public class ClientExntensionApiResource // extends ClientsApiResource 
	{

	
    private final PlatformSecurityContext context;
    private final ToApiJsonSerializer<ClientData> toApiJsonSerializer;
    private final ToApiJsonSerializer<AccountSummaryCollectionData> clientAccountSummaryToApiJsonSerializer;
    private final ApiRequestParameterHelper apiRequestParameterHelper;
    private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;
    private final ClientReadPlatformService clientReadPlatformService;
    private final ClientExtensionReadPlatformService clientExtensionReadPlatformService;
    
    /*
    @Autowired
    public ClientExntensionApiResource(final PlatformSecurityContext context, final ClientReadPlatformService readPlatformService,
            final ToApiJsonSerializer<ClientData> toApiJsonSerializer,
            final ToApiJsonSerializer<AccountSummaryCollectionData> clientAccountSummaryToApiJsonSerializer,
            final ApiRequestParameterHelper apiRequestParameterHelper,
            final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
            final AccountDetailsReadPlatformService accountDetailsReadPlatformService,
            final SavingsAccountReadPlatformService savingsAccountReadPlatformService,
            final ClientExtensionReadPlatformService clientExtensionReadPlatformService) {
    	
    	super(context, readPlatformService, toApiJsonSerializer, clientAccountSummaryToApiJsonSerializer,
            apiRequestParameterHelper, commandsSourceWritePlatformService, accountDetailsReadPlatformService,
            savingsAccountReadPlatformService);
    	this.clientExtensionReadPlatformService = clientExtensionReadPlatformService;

    } */
    
    
    @Autowired
    public ClientExntensionApiResource(final PlatformSecurityContext context,
            final ToApiJsonSerializer<ClientData> toApiJsonSerializer,
            final ToApiJsonSerializer<AccountSummaryCollectionData> clientAccountSummaryToApiJsonSerializer,
            final ApiRequestParameterHelper apiRequestParameterHelper,
            final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService,
            final ClientExtensionReadPlatformService clientExtensionReadPlatformService,
            final ClientReadPlatformService clientReadPlatformService) {
        this.context = context;
        this.toApiJsonSerializer = toApiJsonSerializer;
        this.clientAccountSummaryToApiJsonSerializer = clientAccountSummaryToApiJsonSerializer;
        this.apiRequestParameterHelper = apiRequestParameterHelper;
        this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
        this.clientExtensionReadPlatformService = clientExtensionReadPlatformService;
        this.clientReadPlatformService = clientReadPlatformService;
    }

    @GET
    @Path("{clientId}/template")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveTemplate(@PathParam("clientId") final Long clientId, @Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(ClientExtensionAPIConstants.CLIENT_RESOURCE_NAME);

        ClientData clientData = this.clientReadPlatformService.retrieveOne(clientId);
        
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        
        final ClientExtensionData templateData = this.clientExtensionReadPlatformService.retrieveTemplate(clientData);
        // clientExtensionData = ClientExtensionData.templateOnTop(clientExtensionData, templateData);

        // return this.toApiJsonSerializer.serialize (settings, templateData);
        return this.toApiJsonSerializer.serializeResult(templateData);
    }

    @POST
    @Path("{clientId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String create(@PathParam("clientId") final Long clientId,
    		final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
        		.createClientExtension(clientId)
                .withJson(apiRequestBodyAsJson) //
                .build(); //

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }

    @GET
    @Path("{clientId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveOne(@PathParam("clientId") final Long clientId, @Context final UriInfo uriInfo) {

        this.context.authenticatedUser().validateHasReadPermission(ClientExtensionAPIConstants.CLIENT_RESOURCE_NAME);

        ClientData clientData = this.clientReadPlatformService.retrieveOne(clientId);
        
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        
        final ClientExtensionData clientExtensionData = this.clientExtensionReadPlatformService.retrieveOneByClient(clientId);
        if (clientExtensionData != null) {
        	clientExtensionData.setClient(clientData);
        }

        return this.toApiJsonSerializer.serializeResult(clientExtensionData);
    }
    
    @PUT
    @Path("{clientId}/{extId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String update(@PathParam("clientId") final Long clientId,
    		@PathParam("extId") final Long extId,
    		final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
                .updateClientExtension(clientId, extId) //
                .withJson(apiRequestBodyAsJson) //
                .build(); //

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }


}