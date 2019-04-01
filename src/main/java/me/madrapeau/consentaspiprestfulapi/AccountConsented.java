package me.madrapeau.consentaspiprestfulapi;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMethod;
import javax.persistence.EntityManager;
import javax.persistence.*;
import java.util.Date;

@RestController
public class AccountConsented {
    @PersistenceContext
    EntityManager entityManager;
    @RequestMapping(value = "/accounts/{id}/has_account_details_consent", method = RequestMethod.GET)
    @ResponseBody
    public Boolean getHasAccountDetailsConsent(
            @PathVariable("id") long id) {

        Date currentDate = new Date();
        Account account = entityManager.find(Account.class, id);

        for (Consent c: account.getConsents()) {
            if (c.getExpiryDate().compareTo(currentDate)>0) {
                for (Permission p: c.getPermissions() ) {
                    if(p.getPermissionCode() == "ReadAccountsDetail" && p.getAccount().getId() == account.getId() ){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @RequestMapping(value = "/accounts/{id}/has_balances_consent", method = RequestMethod.GET)
    @ResponseBody
    public Boolean getHasBalancesConsent(
            @PathVariable("id") long id) {

        Date currentDate = new Date();
        Account account = entityManager.find(Account.class, id);

        for (Consent c: account.getConsents()) {
            if (c.getExpiryDate().compareTo(currentDate)>0) {
                for (Permission p: c.getPermissions() ) {
                    if(p.getPermissionCode() == "ReadBalances" && p.getAccount().getId() == account.getId() ){
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @RequestMapping(value = "/accounts/{id}/has_transactions_consent", method = RequestMethod.GET)
    @ResponseBody
    public Boolean getHasTransactionsConsent(
            @PathVariable("id") long id) {

        Date currentDate = new Date();
        Account account = entityManager.find(Account.class, id);

        for (Consent c: account.getConsents()) {
            if (c.getExpiryDate().compareTo(currentDate)>0) {
                for (Permission p: c.getPermissions() ) {
                    if(p.getPermissionCode() == "ReadTransactionsDetail" && p.getAccount().getId() == account.getId() ){
                        return true;
                    }
                }
            }
        }
        return false;
    }
}



