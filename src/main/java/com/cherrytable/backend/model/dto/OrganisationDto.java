package com.cherrytable.backend.model.dto;

import com.cherrytable.backend.model.Project;
import java.util.Set;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class OrganisationDto implements JsonDto {

  private String organisationLogin;
  private String organisationField;
  private String introduction;
  private Set<Project> projects;

  public OrganisationDto() {
  }
}
