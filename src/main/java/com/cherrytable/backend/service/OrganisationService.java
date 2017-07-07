package com.cherrytable.backend.service;

import com.cherrytable.backend.model.Organisation;
import com.cherrytable.backend.model.Project;
import com.cherrytable.backend.model.ResponseType;
import com.cherrytable.backend.model.dto.OrganisationRegisterInputDto;
import com.cherrytable.backend.repository.OrganisationRepository;
import com.cherrytable.backend.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cherrytable.backend.model.dto.OrganisationDto;

@Service
public class OrganisationService {

  private final OrganisationRepository organisationRepository;
  private final ProjectRepository projectRepository;
  private Organisation organisation;
  private OrganisationDto organisationDto;

  @Autowired
  public OrganisationService(
      OrganisationRepository organisationRepository,
      Organisation organisation,
      OrganisationDto organisationDto,
      ProjectRepository projectRepository) {
    this.organisationRepository = organisationRepository;
    this.organisation = organisation;
    this.organisationDto = organisationDto;
    this.projectRepository = projectRepository;
  }

  public boolean isPasswordCorrect(String organisationLogin, String password) {
    return password.equals(organisationRepository.findByOrganisationLogin(organisationLogin)
        .getOrganisationPassword());
  }

  public OrganisationDto getOrganisationByName(String name) {
    return createOrganisationDTO(name);
  }

  public boolean doesOrganisationExistInDB(String name) {
    return organisationRepository.existsByOrganisationLogin(name);
  }

  private OrganisationDto createOrganisationDTO(String name) {
    organisation = organisationRepository.findByOrganisationLogin(name);
    organisationDto.setOrganisationField(organisation.getOrganisationField());
    organisationDto.setIntroduction(organisation.getIntroduction());
    organisationDto.setOrganisationLogin(organisation.getOrganisationLogin());
    return organisationDto;
  }

  public Organisation register(OrganisationRegisterInputDto organisationRegisterInputDto) {
    String name = organisationRegisterInputDto.getOrganisationLogin();
    String password = organisationRegisterInputDto.getPassword();
    Organisation organisation = new Organisation(name, password);
    return organisationRepository.save(organisation);
  }

  public Project addNewProjectToOrganisation(Project project, String organisationName) {
    long projectId = projectRepository.save(project).getId();
    organisation = organisationRepository.findByOrganisationLogin(organisationName);
    organisation.getProjects().add(projectRepository.findOne(projectId));
    organisationRepository.save(organisation);
    return projectRepository.findOne(projectId);
  }
}
