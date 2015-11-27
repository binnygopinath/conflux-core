/**
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this file,
 * You can obtain one at http://mozilla.org/MPL/2.0/.
 */
package org.mifosplatform.organisation.office.domain;

import java.util.LinkedHashMap;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.mifosplatform.infrastructure.core.api.JsonCommand;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Table(name = "m_office_ext")
public class OfficeExt extends AbstractPersistable<Long> {

    @Column(name = "disable_accounting")
    private Boolean disableAccounting;

    public static OfficeExt fromJson(final JsonCommand command) {

        final Boolean disableAccounting = command.booleanObjectValueOfParameterNamed("disableAccounting");
        return new OfficeExt(disableAccounting);
    }

    protected OfficeExt() {
        this.disableAccounting = null;
    }

    OfficeExt(final Boolean disableAccounting) {
        this.disableAccounting = disableAccounting;
    }

    public Map<String, Object> update(final JsonCommand command) {

        final Map<String, Object> actualChanges = new LinkedHashMap<>(7);

        final String disableAccountingParamName = "disableAccounting";

        if (command.isChangeInBooleanParameterNamed(disableAccountingParamName, this.disableAccounting)) {
            final Boolean newValue = command.booleanObjectValueOfParameterNamed(disableAccountingParamName);
            actualChanges.put(disableAccountingParamName, newValue);
            this.disableAccounting = newValue;
        }

        return actualChanges;
    }

    public Boolean accountingDisabled() {
        return this.disableAccounting;
    }

}