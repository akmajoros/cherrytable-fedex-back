package com.cherrytable.backend.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrganisationRegisterInputDto implements JsonDto {

  @NotNull
  private String organisationLogin;
  @NotNull
  private String password;


}
