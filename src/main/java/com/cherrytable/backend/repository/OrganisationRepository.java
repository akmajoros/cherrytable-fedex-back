package com.cherrytable.backend.repository;

import com.cherrytable.backend.model.Organisation;
import org.springframework.data.repository.CrudRepository;

public interface OrganisationRepository extends CrudRepository<Organisation, Long> {

  Organisation findByOrganisationLogin(String organisationLogin);

  boolean existsByOrganisationLogin(String organisationLogin);
}
