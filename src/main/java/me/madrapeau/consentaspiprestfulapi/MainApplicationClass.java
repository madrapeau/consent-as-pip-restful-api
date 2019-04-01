package me.madrapeau.consentaspiprestfulapi;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;
import java.text.*;

@SpringBootApplication
public class MainApplicationClass implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(MainApplicationClass.class);

    public static void main(String args[]) {
        SpringApplication.run(MainApplicationClass.class, args);
    }

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... strings) throws Exception {

        log.info("Creating tables");

        //Consents
        jdbcTemplate.execute("DROP TABLE CONSENTS.PUBLIC.consent IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE CONSENTS.PUBLIC.consent(" +
                "id INTEGER, consumer_id INTEGER, organisation_id INTEGER, organisation_name VARCHAR(255), created_date DATE, expiry_date DATE)");
        //Accounts
        jdbcTemplate.execute("DROP TABLE CONSENTS.PUBLIC.account IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE CONSENTS.PUBLIC.account(" +
                "id INTEGER, account_number VARCHAR(255), organisation_id INTEGER)");
        //Consent_account
        jdbcTemplate.execute("DROP TABLE CONSENTS.PUBLIC.consent_account IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE CONSENTS.PUBLIC.consent_account(" +
                "id INTEGER, consent_id INTEGER, account_id INTEGER)");
        //Permissions
        jdbcTemplate.execute("DROP TABLE CONSENTS.PUBLIC.consent_permission IF EXISTS;");
        jdbcTemplate.execute("CREATE TABLE CONSENTS.PUBLIC.consent_permission(" +
                "id INTEGER, consent_id INTEGER, account_id INTEGER, permission_code VARCHAR(255))");


        // Create list of access record
        DateFormat formatter;
        Date created_date;
        Date expiry_date;
        formatter = new SimpleDateFormat("dd-MMM-yy");
        List<Object[]> consentsList = new ArrayList<Object[]>();
        List<Object[]> accountsList = new ArrayList<Object[]>();
        List<Object[]> consent_accountList = new ArrayList<Object[]>();
        List<Object[]> consent_permissionList = new ArrayList<Object[]>();

        String str_created_date = "01-January-18";
        String str_expiry_date = "01-February-18";
        created_date = formatter.parse(str_created_date);
        expiry_date = formatter.parse(str_expiry_date);
        Object[] a = {1,20001, 50001, "Madsoft inc.", created_date, expiry_date};
        consentsList.add(a);
        str_created_date = "01-January-18";
        str_expiry_date = "01-January-19";
        created_date = formatter.parse(str_created_date);
        expiry_date = formatter.parse(str_expiry_date);
        Object[] b = {2,20001, 50002, "Stark Industries inc.", created_date, expiry_date};
        consentsList.add(b);
        str_created_date = "01-January-18";
        str_expiry_date = "01-January-20";
        created_date = formatter.parse(str_created_date);
        expiry_date = formatter.parse(str_expiry_date);
        Object[] c = {3,20001, 50003, "ACME inc.", created_date, expiry_date};
        consentsList.add(c);
        str_created_date = "01-January-18";
        str_expiry_date = "01-June-19";
        created_date = formatter.parse(str_created_date);
        expiry_date = formatter.parse(str_expiry_date);
        Object[] d = {4,20001, 50001, "Madsoft inc.", created_date, expiry_date};
        consentsList.add(d);
        // Use stream to print out each tuple of the list
        consentsList.forEach(consent -> log.info(String.format("Inserting consent id %s for organisation %s", consent[0], consent[2])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load consent data
        jdbcTemplate.batchUpdate("INSERT INTO CONSENTS.PUBLIC.consent (id, consumer_id, organisation_id, organisation_name, created_date, expiry_date) VALUES (?,?,?,?,?,?)", consentsList);

        Object[] aa = {12205, "0006.00011.12205",50003};
        accountsList.add(aa);
        Object[] bb = {15000, "0006.00011.15000",50003};
        accountsList.add(bb);
        Object[] cc = {24000, "0006.00011.24000",50003};
        accountsList.add(cc);
        Object[] dd = {25000, "0006.00011.25000",50003};
        accountsList.add(dd);
        Object[] ee = {34999, "0006.00011.34999",50003};
        accountsList.add(ee);

        // Use stream to print out each tuple of the list
        accountsList.forEach(account -> log.info(String.format("Inserting account %s", account[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load account data
        jdbcTemplate.batchUpdate("INSERT INTO CONSENTS.PUBLIC.account (id, account_number, organisation_id) VALUES (?,?,?)", accountsList);

        /*Object[] aaa = {1,3,15000};
        consent_accountList.add(aaa);
        Object[] bbb = {2,3,24000};
        consent_accountList.add(bbb);
        Object[] ccc = {3,3,34999};
        consent_accountList.add(ccc);

        // Use stream to print out each tuple of the list
        consent_accountList.forEach(consent_account -> log.info(String.format("Inserting account %s for consent id %s", consent_account[2], consent_account[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load consent_permission data
        jdbcTemplate.batchUpdate("INSERT INTO CONSENTS.PUBLIC.consent_permission (id, consent_id, account_id, permission_code) VALUES (?,?,?,?)", consent_permissionList);*/

        Object[] aaaa = {1,3,15000, "ReadAccountsDetail"};
        consent_permissionList.add(aaaa);
        Object[] bbbb = {2,3,24000, "ReadAccountsDetail"};
        consent_permissionList.add(bbbb);
        Object[] cccc = {3,3,34999, "ReadAccountsDetail"};
        consent_permissionList.add(cccc);
        Object[] dddd = {4,3,15000, "ReadBalances"};
        consent_permissionList.add(dddd);
        Object[] eeee = {5,3,24000, "ReadBalances"};
        consent_permissionList.add(eeee);
        Object[] ffff = {6,3,34999, "ReadBalances"};
        consent_permissionList.add(ffff);
        Object[] gggg = {7,3,15000, "ReadTransactionsDetail"};
        consent_permissionList.add(gggg);
        Object[] hhhh = {8,3,24000, "ReadTransactionsDetail"};
        consent_permissionList.add(hhhh);
        Object[] iiii = {9,3,34999, "ReadTransactionsDetail"};
        consent_permissionList.add(iiii);

        // Use stream to print out each tuple of the list
        consent_permissionList.forEach(consent_permission -> log.info(String.format("Inserting permission (%s) for account %s for consent id %s", consent_permission[3], consent_permission[2], consent_permission[1])));

        // Uses JdbcTemplate's batchUpdate operation to bulk load consent_permission data
        jdbcTemplate.batchUpdate("INSERT INTO CONSENTS.PUBLIC.consent_permission (id, consent_id, account_id, permission_code) VALUES (?,?,?,?)", consent_permissionList);

    }
}