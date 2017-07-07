package com.cherrytable.backend.model;

import org.springframework.stereotype.Component;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Set;

@Entity
@Table(name = "organisation")
@Component
public class Organisation {

  @Id
  @NotNull
  @Column (name = "organisation_login")
  private String organisationLogin;
  @NotNull
  @Column (name = "organisation_password")
  private String organisationPassword;
  @Column (name = "organisation_field")
  private String organisationField;
  @Column (name = "introduction")
  private String introduction;
  @Column (name = "organisation_token")
  private String organisationToken;

  @OneToMany
  private Set<Project> projects;

  public Organisation() {
  }

  public Organisation(String organisationLogin, String organisationPassword) {
    this.organisationLogin = organisationLogin;
    this.organisationPassword = organisationPassword;
  }

  public String getOrganisationLogin() {
    return organisationLogin;
  }

  public void setOrganisationLogin(String organisationLogin) {
    this.organisationLogin = organisationLogin;
  }

  public String getOrganisationField() {
    return organisationField;
  }

  public void setOrganisationField(String organisationField) {
    this.organisationField = organisationField;
  }

  public String getIntroduction() {
    return introduction;
  }

  public void setIntroduction(String introduction) {
    this.introduction = introduction;
  }

  public Set<Project> getProjects() {
    return projects;
  }

  public void setProjects(Set<Project> projects) {
    this.projects = projects;
  }

  public String getOrganisationToken() {
    return organisationToken;
  }

  public void setOrganisationToken(String organisationToken) {
    this.organisationToken = organisationToken;
  }

  public String getOrganisationPassword() {
    return organisationPassword;
  }

  public void setOrganisationPassword(String organisationPassword) {
    this.organisationPassword = organisationPassword;
  }
}
