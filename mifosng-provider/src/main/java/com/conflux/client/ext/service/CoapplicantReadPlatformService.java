package com.conflux.client.ext.service;

import org.mifosplatform.portfolio.client.domain.Client;

import com.conflux.client.ext.data.CoapplicantDetailsData;

public interface CoapplicantReadPlatformService {

	CoapplicantDetailsData retrieveCoapplicantDetailsDataTemplate(final Client client);

}
