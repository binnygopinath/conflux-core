package org.conflux.client.ext.service;

import org.mifosplatform.portfolio.client.domain.Client;
import org.conflux.client.ext.data.CoapplicantDetailsData;

public interface CoapplicantReadPlatformService {

	CoapplicantDetailsData retrieveCoapplicantDetailsDataTemplate(final Client client);

}
