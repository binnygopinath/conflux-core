package org.conflux.client.ext.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.joda.time.LocalDate;
import org.mifosplatform.infrastructure.codes.domain.CodeValue;
import org.mifosplatform.portfolio.client.domain.Client;
import org.springframework.data.jpa.domain.AbstractPersistable;

@SuppressWarnings("serial")
@Entity
@Table(name = "ct_coapplicant")
public class Coapplicant extends AbstractPersistable<Long> {

	@ManyToOne(optional = false)
	@JoinColumn(name = "client_id", nullable = false)
	private Client client;
	
	@Column(name = "first_name", length = 50, nullable = false)
	private String firstName;

	@Column(name = "middle_name", length = 50)
	private String middleName;

	@Column(name = "last_name", length = 50)
	private String lastName;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "sp_relationship_cv_id", nullable = false)
	private CodeValue relationship;
	
	@Column(name = "date_of_birth", nullable = true)
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

	@Column(name = "age", length = 3, nullable = false)
	private Integer age;
	
	@Column(name = "mothers_maiden_name", length = 150, nullable = false)
	private String mothersMaidenName;

	@Column(name = "email_id", length = 150)
	private String emailId;
	
	@Column(name = "father_first_name", length = 50, nullable = false)
	private String fatherFirstName;

	@Column(name = "father_middle_name", length = 50)
	private String fatherMiddleName;

	@Column(name = "father_last_name", length = 50)
	private String fatherLastName;
	
	protected Coapplicant(){
		//
	}

	public static Coapplicant createFrom(final Client client,
			final String firstName, final String middleName,
			final String lastName, final CodeValue relationship,
			final LocalDate dateOfBirth, final Integer age,
			final String mothersMaidenName, final String emailId,
			final String fatherFirstName,final String fatherMiddleName,
			final String fatherLastName) {

		return new Coapplicant(client, firstName, middleName, lastName,
				relationship, dateOfBirth, age, mothersMaidenName,
				emailId,fatherFirstName,fatherMiddleName,fatherLastName);
	}
	
	private Coapplicant(final Client client,
			final String firstName, final String middleName,
			final String lastName, final CodeValue relationship,
			final LocalDate dateOfBirth, final Integer age,
			final String mothersMaidenName, final String emailId,
			final String fatherFirstName,final String fatherMiddleName,
			final String fatherLastName) {
		
		this.client = client;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.relationship = relationship;
		if(dateOfBirth != null){
			this.dateOfBirth = dateOfBirth.toDateTimeAtStartOfDay().toDate();
		}
		this.age = age;
		this.mothersMaidenName = mothersMaidenName;
		this.emailId = emailId;
		this.fatherFirstName=fatherFirstName;
		this.fatherMiddleName=fatherMiddleName;
		this.fatherLastName=fatherLastName;
	}

	public void update(final Client client,
			final String firstName, final String middleName,
			final String lastName, final CodeValue relationship,
			final LocalDate dateOfBirth, final Integer age,
			final String mothersMaidenName, final String emailId, 
		    final String fatherFirstName,final String fatherMiddleName,
		    final String fatherLastName ){  
		this.client = client;
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.relationship = relationship;
		if (dateOfBirth != null) {
			this.dateOfBirth = dateOfBirth.toDateTimeAtStartOfDay().toDate();
		}
		this.age = age;
		this.mothersMaidenName = mothersMaidenName;
		this.emailId = emailId;
		this.fatherFirstName=fatherFirstName;
		this.fatherMiddleName=fatherMiddleName;
		this.fatherLastName=fatherLastName;
	}
	
	public void updateClient(final Client client) {
		this.client = client;		
	}

	public Client getClient() {
		return client;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public CodeValue getRelationship() {
		return relationship;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public Integer getAge() {
		return age;
	}

	public String getMothersMaidenName() {
		return mothersMaidenName;
	}

	public String getEmailId() {
		return emailId;
	}

	public String getFatherFirstName() {
		return fatherFirstName;
	}

	public void setFatherFirstName(String fatherFirstName) {
		this.fatherFirstName = fatherFirstName;
	}

	public String getFatherMiddleName() {
		return fatherMiddleName;
	}

	public void setFatherMiddleName(String fatherMiddleName) {
		this.fatherMiddleName = fatherMiddleName;
	}

	public String getFatherLastName() {
		return fatherLastName;
	}

	public void setFatherLastName(String fatherLastName) {
		this.fatherLastName = fatherLastName;
	}
	
	
}
