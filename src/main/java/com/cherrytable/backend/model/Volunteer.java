package com.cherrytable.backend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "volunteer")
public class Volunteer {

  @Id
  @NotNull
  @Column(name = "volunteer_login")
  private String volunteerLogin;
  @NotNull
  @Column(name = "volunteer_password")
  private String volunteerPassword;
  @Column(name = "first_name")
  private String firstName;
  @Column(name = "last_name")
  private String lastName;
  @Column(name = "user_token")
  private String userToken;
  
  public Volunteer() {
  }

  public String getVolunteerLogin() {
    return volunteerLogin;
  }

  public void setVolunteerLogin(String volunteerLogin) {
    this.volunteerLogin = volunteerLogin;
  }

  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public String getVolunteerPassword() {
    return volunteerPassword;
  }

  public void setVolunteerPassword(String volunteerPassword) {
    this.volunteerPassword = volunteerPassword;
  }

  public String getUserToken() {
    return userToken;
  }

  public void setUserToken(String userToken) {
    this.userToken = userToken;
  }
}
