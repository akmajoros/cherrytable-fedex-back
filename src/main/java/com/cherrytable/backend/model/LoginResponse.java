package com.cherrytable.backend.model;

import org.springframework.stereotype.Component;

@Component
public class LoginResponse implements ResponseType{

  private String organisationLogin;
  private String token;

  public LoginResponse() {
  }

  public LoginResponse(String token, String organisationLogin) {
    this.token = token;
    this.organisationLogin = organisationLogin;
  }

  public String getToken() {
    return token;
  }

  public void setToken(String token) {
    this.token = token;
  }

  public String getOrganisationLogin() {
    return organisationLogin;
  }

  public void setOrganisationLogin(String organisationLogin) {
    this.organisationLogin = organisationLogin;
  }
}
