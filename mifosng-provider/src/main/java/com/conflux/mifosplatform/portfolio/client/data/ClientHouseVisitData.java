
package com.conflux.mifosplatform.portfolio.client.data;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.mifosplatform.organisation.staff.data.StaffData;
import org.mifosplatform.useradministration.domain.AppUser;

/**
 * Immutable data object representing client relationships data.
 */
final public class ClientHouseVisitData {
    private final Long id;
    private final LocalDate plannedHouseVisitDate;
    private final LocalDateTime actualHouseVisitDateTime;
    private final StaffData houseVisitByStaff1;
    private final StaffData houseVisitByStaff2;
    private final String comments;
    private final AppUser updatedBy;
    private final LocalDateTime updatedOn;

    public static ClientHouseVisitData instance(
    		final Long id,
    		final LocalDate plannedHouseVisitDate,
    		final LocalDateTime actualHouseVisitDateTime,
    		final StaffData houseVisitByStaff1,
    		final StaffData houseVisitByStaff2,
    		final String comments,
    		final AppUser updatedBy,
    		final LocalDateTime updatedOn
    		) {

        return new ClientHouseVisitData(id, 
        		plannedHouseVisitDate, actualHouseVisitDateTime,
        		houseVisitByStaff1, houseVisitByStaff2, comments, updatedBy, updatedOn
        		);
    }

    private ClientHouseVisitData(
    		final Long id,
    		final LocalDate plannedHouseVisitDate,
    		final LocalDateTime actualHouseVisitDateTime,
    		final StaffData houseVisitByStaff1,
    		final StaffData houseVisitByStaff2,
    		final String comments,
    		final AppUser updatedBy,
    		final LocalDateTime updatedOn
		) {
	    this.id = id;
	    this.plannedHouseVisitDate = plannedHouseVisitDate;
	    this.actualHouseVisitDateTime = actualHouseVisitDateTime;
	    this.houseVisitByStaff1 = houseVisitByStaff1;
	    this.houseVisitByStaff2 = houseVisitByStaff2;
	    this.comments = comments;
	    this.updatedBy = updatedBy;
	    this.updatedOn = updatedOn;
    }

    public Long id() {
        return this.id;
    }

    public LocalDate plannedHouseVisitDate() {
        return this.plannedHouseVisitDate;
    }

    public LocalDateTime actualHouseVisitDateTime() {
        return this.actualHouseVisitDateTime;
    }

    public StaffData houseVisitByStaff1() {
        return this.houseVisitByStaff1;
    }

    public StaffData houseVisitByStaff2() {
        return this.houseVisitByStaff2;
    }

    public String comments() {
        return this.comments;
    }
    
    public AppUser updatedBy() {
        return this.updatedBy;
    }

    public LocalDateTime updatedOn() {
        return this.updatedOn;
    }
    
}