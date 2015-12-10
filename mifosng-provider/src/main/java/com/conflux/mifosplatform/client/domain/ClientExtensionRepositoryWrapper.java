/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package com.conflux.mifosplatform.client.domain;

import org.mifosplatform.infrastructure.security.service.PlatformSecurityContext;
import org.mifosplatform.portfolio.client.exception.ClientNotActiveException;
import org.mifosplatform.portfolio.client.exception.ClientNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * Wrapper for {@link ClientExtensionRepository} that adds NULL checking and Error
 * handling capabilities
 * </p>
 */
@Service
public class ClientExtensionRepositoryWrapper {

    private final ClientExtensionRepository repository;
    private final PlatformSecurityContext context;

    @Autowired
    public ClientExtensionRepositoryWrapper(final ClientExtensionRepository repository, final PlatformSecurityContext context) {
        this.repository = repository;
        this.context = context;
    }

    public ClientExtension findOneWithNotFoundDetection(final Long id) {
        final ClientExtension clientext = this.repository.findOne(id);
        if (clientext == null) { throw new ClientNotFoundException(id); }
        return clientext;
    }

    public void save(final ClientExtension clientext) {
        this.repository.save(clientext);
    }

    public void saveAndFlush(final ClientExtension clientext) {
        this.repository.saveAndFlush(clientext);
    }

    public void delete(final ClientExtension clientext) {
        this.repository.delete(clientext);
    }

}