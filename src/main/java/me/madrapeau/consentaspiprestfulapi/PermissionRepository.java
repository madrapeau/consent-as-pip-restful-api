package me.madrapeau.consentaspiprestfulapi;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "permissions", path = "permissions")
public interface PermissionRepository extends PagingAndSortingRepository<Permission, Long> {

}