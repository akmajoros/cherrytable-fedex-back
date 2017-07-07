package com.cherrytable.backend.controller;

import com.cherrytable.backend.model.Project;
import com.cherrytable.backend.service.OrganisationService;
import com.cherrytable.backend.service.ProjectService;
import com.cherrytable.backend.service.StatusService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrganisationController {

  @Autowired
  OrganisationService organisationService;

  @Autowired
  StatusService statusService;

  @Autowired
  ProjectService projectService;

  @GetMapping("/organisation/{id}")
  public ResponseEntity<?> getOrganisation(@PathVariable("id") String name,
      @RequestHeader String token) {
    if (!organisationService.doesOrganisationExistInDB(name)) {
      return new ResponseEntity(statusService.responseToNotExistsingOrganisation(),
          HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(organisationService.getOrganisationByName(name), HttpStatus.OK);
  }

  @GetMapping("/organisation/{id}/projects")
  public ResponseEntity<?> getOrganisationsProjects(@PathVariable("id") String name,
      @RequestHeader String token) {
    if (!organisationService.doesOrganisationExistInDB(name)) {
      return new ResponseEntity(statusService.responseToNotExistsingOrganisation(),
          HttpStatus.NOT_FOUND);
    }
    return new ResponseEntity(
        projectService.getProjectsOfOrganisation(organisationService.getOrganisationByName(name)),
        HttpStatus.OK);
    }

  @PostMapping("/organisation/{id}/projects")
  public ResponseEntity<?> addNewProject(
      @PathVariable("id") String name,
      @RequestBody @Valid Project project,
      BindingResult bindingResult,
      @RequestHeader String token) {
    if (bindingResult.hasErrors()){
      return ResponseEntity.badRequest().body(statusService.responseToMissingParameters(bindingResult));
    }
    return ResponseEntity.ok().body(organisationService.addNewProjectToOrganisation(project, name));
    }
  }
