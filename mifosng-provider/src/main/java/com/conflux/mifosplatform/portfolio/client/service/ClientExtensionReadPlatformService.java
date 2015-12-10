package com.conflux.mifosplatform.portfolio.client.service;

import java.util.Collection;

import org.mifosplatform.portfolio.client.data.ClientData;

import com.conflux.mifosplatform.common.AddressData;
import com.conflux.mifosplatform.portfolio.client.data.ClientExtensionData;

public interface ClientExtensionReadPlatformService {

    ClientExtensionData retrieveTemplate(ClientData client);

    ClientExtensionData retrieveOne (Long id);
    
    ClientExtensionData retrieveOneByClient (Long clientId);

	Collection<AddressData> retrieveClientAddresses(Long clientId);

}