package org.conflux.client.ext.api;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
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
import org.mifosplatform.portfolio.client.api.ClientApiConstants;
import org.conflux.client.ext.data.CoapplicantDetailsData;
import org.conflux.client.ext.service.CoapplicantReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Path("/coapplicant")
@Component
@Scope("singleton")
public class CoapplicantApiResource {

	private final PlatformSecurityContext context;
	private final ApiRequestParameterHelper apiRequestParameterHelper;
	private final ToApiJsonSerializer<CoapplicantDetailsData> toApiJsonSerializer;
	private final CoapplicantReadPlatformService coapplicantReadPlatformService;
	private final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService;

	@Autowired
	public CoapplicantApiResource(
			final PlatformSecurityContext context,final ApiRequestParameterHelper apiRequestParameterHelper,
			final ToApiJsonSerializer<CoapplicantDetailsData> toApiJsonSerializer,
			final CoapplicantReadPlatformService coapplicantReadPlatformService,
			final PortfolioCommandSourceWritePlatformService commandsSourceWritePlatformService) {
		this.context = context;
		this.apiRequestParameterHelper = apiRequestParameterHelper;
		this.toApiJsonSerializer = toApiJsonSerializer;
		this.coapplicantReadPlatformService = coapplicantReadPlatformService;
		this.commandsSourceWritePlatformService = commandsSourceWritePlatformService;
	}

	@GET
	@Path("template")
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String retrieveCoapplicantTemplate(@Context final UriInfo uriInfo,@QueryParam("commandParam") final String commandParam) {

        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
        
        CoapplicantDetailsData coapplicantDetailsData = this.coapplicantReadPlatformService.retrieveCoapplicantDetailsDataTemplate(null);
        
        return this.toApiJsonSerializer.serialize(settings, coapplicantDetailsData);
	}
	
	@POST
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String create(final String apiRequestBodyAsJson) {

        final CommandWrapper commandRequest = new CommandWrapperBuilder() //
                .createCoClient() //
                .withJson(apiRequestBodyAsJson) //
                .build(); //

        final CommandProcessingResult result = this.commandsSourceWritePlatformService.logCommandSource(commandRequest);

        return this.toApiJsonSerializer.serialize(result);
    }
	@GET
    @Path("{coapplicantId}")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveOneCoapplicantById(@PathParam("coapplicantId") final Long coapplicantId,@Context final UriInfo uriInfo,@QueryParam("commandParam") final String commandParam){
		this.context.authenticatedUser().validateHasReadPermission(ClientApiConstants.CLIENT_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		
		return null;
	}
	
	@GET
	@Path("{clientId}/coapplicants")
    @Consumes({ MediaType.APPLICATION_JSON })
    @Produces({ MediaType.APPLICATION_JSON })
    public String retrieveAllCoapplicantByClientId(@PathParam("clientId") final Long clientId,@Context final UriInfo uriInfo,@QueryParam("commandParam") final String commandParam){
		this.context.authenticatedUser().validateHasReadPermission(ClientApiConstants.CLIENT_RESOURCE_NAME);
        final ApiRequestJsonSerializationSettings settings = this.apiRequestParameterHelper.process(uriInfo.getQueryParameters());
		
		return null;
	}

}
