package com.cherrytable.backend.model.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VolunteerDto implements JsonDto {

  private String loginName;
  private String firstName;
  private String lastName;
}
