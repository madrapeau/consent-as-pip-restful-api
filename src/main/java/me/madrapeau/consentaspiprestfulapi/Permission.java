package me.madrapeau.consentaspiprestfulapi;

import javax.persistence.*;
import org.springframework.data.rest.core.annotation.*;

@Entity
@Table(name = "consent_permission")
// Define a sequence - might also be in another class:
@SequenceGenerator(name="seq", initialValue=100, allocationSize=100)
public class Permission {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq")
    private long id;

    @Column
    private String permission_code;

    @ManyToOne
    @JoinColumn(name="consent_id")
    private Consent consent;

    @OneToOne
    @JoinColumn(name = "account_id")
    @RestResource(path = "account", rel="account")
    private Account account;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getPermissionCode() {
        return permission_code;
    }

    public void setPermissionCode(String permission_code) {
        this.permission_code = permission_code;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }
}
