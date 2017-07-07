package com.cherrytable.backend.controller;

import com.cherrytable.backend.model.Organisation;
import com.cherrytable.backend.model.ResponseType;
import com.cherrytable.backend.model.dto.OrganisationRegisterInputDto;
import com.cherrytable.backend.service.OrganisationService;
import com.cherrytable.backend.service.StatusService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

  private final StatusService statusService;
  private final OrganisationService organisationService;

  @Autowired
  public RegistrationController(StatusService statusService,
      OrganisationService organisationService) {
    this.statusService = statusService;
    this.organisationService = organisationService;
  }

  @PostMapping(value = "/register")
  public ResponseEntity<ResponseType> registerUser(
      @RequestBody @Valid OrganisationRegisterInputDto organisationRegisterInputDto,
      BindingResult bindingResult) {

    if(bindingResult.hasErrors()) {
      return ResponseEntity.badRequest().body(statusService.responseToMissingParameters(bindingResult));
    }

    if(organisationService.doesOrganisationExistInDB(organisationRegisterInputDto.getOrganisationLogin())) {
      return ResponseEntity.badRequest().body(statusService.getOccupiedOrganisationLoginStatus());
    }

    Organisation organisation = organisationService.register(organisationRegisterInputDto);
    return ResponseEntity.ok().body(statusService.getRegistrationSuccessStatus());
  }
}
