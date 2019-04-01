package me.madrapeau.consentaspiprestfulapi;


import javax.persistence.*;

import java.util.Date;
import java.util.List;


@Entity
@Table(name = "consent")
// Define a sequence - might also be in another class:
@SequenceGenerator(name="seq", initialValue=100, allocationSize=100)
public class Consent {
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
	private long id;

	@Column
	private long consumerId;

	@Column
	private long organisationId;

	@Column
	private String organisationName;

	@Column
	private Date createdDate;

	@Column
	private Date expiryDate;

	@ManyToMany(mappedBy = "consents")
	private List<Account> accounts;

	@OneToMany(mappedBy = "consent")
	private List<Permission> permissions;

	public long getConsumerId() {
		return consumerId;
	}

	public void setConsumerId(long consumerId) {
		this.consumerId = consumerId;
	}

	public long getOrganisationId() {
		return organisationId;
	}

	public void setOrganisationId(long organisationId) {
		this.organisationId = organisationId;
	}

	public String getOrganisationName() {
		return organisationName;
	}

	public void setOrganisationName(String organisationName) {
		this.organisationName = organisationName;
	}

	public Date getCreatedDate() { return createdDate; }

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public List<Permission> getPermissions() {
		return permissions;
	}

}

