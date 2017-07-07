package com.cherrytable.backend.controller;

import com.cherrytable.backend.model.LoginResponse;
import com.cherrytable.backend.model.ResponseType;
import com.cherrytable.backend.model.dto.JsonDto;
import com.cherrytable.backend.model.dto.OrgLoginInputDto;
import com.cherrytable.backend.service.OrganisationService;
import com.cherrytable.backend.service.StatusService;
import com.cherrytable.backend.service.TokenService;
import javax.xml.bind.SchemaOutputResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
public class LoginController {

  private final StatusService statusService;
  private final OrganisationService organisationService;
  private final TokenService tokenService;

  @Autowired
  public LoginController(StatusService statusService, OrganisationService organisationService,
                         TokenService tokenService) {
    this.statusService = statusService;
    this.organisationService = organisationService;
    this.tokenService = tokenService;
  }

  @PostMapping("/login")
  public ResponseEntity<ResponseType> loginOrganisation (
      @RequestBody @Valid OrgLoginInputDto orgLoginInputDto,
      BindingResult bindingResult) {
    if (bindingResult.hasErrors()){
      return ResponseEntity.badRequest().body(statusService.responseToMissingParameters(bindingResult));
    }
    if (!organisationService.doesOrganisationExistInDB(orgLoginInputDto.getOrganisationLogin())){
      return ResponseEntity.status(401).body(statusService.responseToIncorrectOrgLogin());
    }
    if (!organisationService.isPasswordCorrect(orgLoginInputDto.getOrganisationLogin(),
            orgLoginInputDto.getPassword())){
      return ResponseEntity.status(401).body(statusService.responseToIncorrectPassword());
    }
    String savedToken = tokenService.saveNewTokenToOrg(orgLoginInputDto.getOrganisationLogin());
    return ResponseEntity.ok().body(new LoginResponse(savedToken, orgLoginInputDto.getOrganisationLogin()));
  }
}
