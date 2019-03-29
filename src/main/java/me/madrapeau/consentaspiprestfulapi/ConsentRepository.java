package me.madrapeau.consentaspiprestfulapi;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(collectionResourceRel = "consents", path = "consents")
public interface ConsentRepository extends PagingAndSortingRepository<Consent, Long> {

	List<Consent> findByOrganisationName(@Param("org_name") String org_name);

}