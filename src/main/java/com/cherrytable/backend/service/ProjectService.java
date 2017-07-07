package com.cherrytable.backend.service;

import com.cherrytable.backend.model.Project;
import com.cherrytable.backend.model.ResponseType;
import com.cherrytable.backend.model.dto.OrganisationDto;
import java.util.Set;
import org.springframework.stereotype.Component;

@Component
public class ProjectService {

  public Set<Project> getProjectsOfOrganisation(OrganisationDto organisation) {
    return organisation.getProjects();
  }

}
