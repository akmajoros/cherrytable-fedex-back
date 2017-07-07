package com.cherrytable.backend.service;

import com.cherrytable.backend.model.Organisation;
import com.cherrytable.backend.model.dto.OrganisationRegisterInputDto;
import com.cherrytable.backend.repository.OrganisationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.cherrytable.backend.model.dto.OrganisationDto;

@Service
public class OrganisationService {

  private final OrganisationRepository organisationRepository;

  @Autowired
  public OrganisationService(OrganisationRepository organisationRepository) {
    this.organisationRepository = organisationRepository;
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
    Organisation organisation = organisationRepository.findByOrganisationLogin(name);
    OrganisationDto organisationDto = new OrganisationDto();
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
}
