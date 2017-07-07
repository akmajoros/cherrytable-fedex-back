package com.cherrytable.backend.service;

import com.cherrytable.backend.model.Project;
import com.cherrytable.backend.model.ResponseType;
import com.cherrytable.backend.model.dto.OrganisationDto;
import com.cherrytable.backend.repository.ProjectRepository;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProjectService {

  private ProjectRepository projectRepository;

  @Autowired
  public ProjectService(ProjectRepository projectRepository) {
    this.projectRepository = projectRepository;
  }

  public Set<Project> getProjectsOfOrganisation(OrganisationDto organisation) {
    return organisation.getProjects();
  }

  public Iterable<Project> getAllProjects() {
    return projectRepository.findAll();
  }
}
