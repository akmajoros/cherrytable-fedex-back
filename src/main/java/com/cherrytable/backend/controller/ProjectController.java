package com.cherrytable.backend.controller;

import com.cherrytable.backend.model.Project;
import com.cherrytable.backend.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProjectController {

  private ProjectService projectService;

  @Autowired
  public ProjectController(ProjectService projectService) {
    this.projectService = projectService;
  }

  @GetMapping("/allprojects")
  public Iterable<Project> getAllProjects() {
    return projectService.getAllProjects();
  }

}
