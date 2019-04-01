package me.madrapeau.consentaspiprestfulapi;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "account")
// Define a sequence - might also be in another class:
@SequenceGenerator(name="seq", initialValue=100, allocationSize=100)
public class Account {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private long id;

    //@Column
    //private long accountId;
    @Column
    private long organisationId;
    @Column
    private String accountNumber;
    //@ManyToOne
    //@JoinColumn(name="consent_id")
    //private Consent consent;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "consent_permission",
            joinColumns = @JoinColumn(name = "account_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "consent_id",
                    referencedColumnName = "id"))
    private List<Consent> consents;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getOrganisationId() {
        return organisationId;
    }

    public void setOrganisationId(long organisationId) {
        this.organisationId = organisationId;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public List<Consent> getConsents() {
        return consents;
    }

}