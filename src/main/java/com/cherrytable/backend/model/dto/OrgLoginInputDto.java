package com.cherrytable.backend.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
public class OrgLoginInputDto implements JsonDto{

  @NotNull
  private String organisationLogin;
  @NotNull
  private String password;
}
