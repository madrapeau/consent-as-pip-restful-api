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
    @RequestMapping(value = "/accounts/{id}/has_been_consented", method = RequestMethod.GET)
    @ResponseBody
    public Boolean getIsAccountConsented(
            @PathVariable("id") long id) {

        Date currentDate = new Date();
        Account account = entityManager.find(Account.class, id);

        for (Consent c: account.getConsents()) {
            if (c.getExpiryDate().compareTo(currentDate)>0) {
                return true;
            }
        }
        return false;
    }

}



