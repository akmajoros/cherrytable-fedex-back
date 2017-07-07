package com.cherrytable.backend.model;

import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "project")
public class Project {

  @Id
  @NotNull
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private long id;
  @NotNull
  @Column(name = "project_name")
  private String projectName;
  @Column(name = "description")
  private String description;
  @Column(name = "expiry_date")
  private String expiryDate;
  @Column(name = "event_date")
  private  String eventDate;
  @Column(name = "volunteer_limit")
  private int volunteerLimit;

  @ManyToMany
  @JoinTable(
      name = "volunteer_to_project",
      joinColumns = @JoinColumn(name = "id", referencedColumnName = "id"),
      inverseJoinColumns = @JoinColumn(name = "volunteer_login", referencedColumnName = "volunteer_login"))
  Set<Volunteer> volunteerSet;

  public Project() {
  }

  public long getId() {
    return id;
  }

  public void setId(long id) {
    this.id = id;
  }

  public String getProjectName() {
    return projectName;
  }

  public void setProjectName(String projectName) {
    this.projectName = projectName;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public String getExpiryDate() {
    return expiryDate;
  }

  public void setExpiryDate(String expiryDate) {
    this.expiryDate = expiryDate;
  }

  public String getEventDate() {
    return eventDate;
  }

  public void setEventDate(String eventDate) {
    this.eventDate = eventDate;
  }

  public int getVolunteerLimit() {
    return volunteerLimit;
  }

  public void setVolunteerLimit(int volunteerLimit) {
    this.volunteerLimit = volunteerLimit;
  }

  public Set<Volunteer> getVolunteerSet() {
    return volunteerSet;
  }

  public void setVolunteerSet(Set<Volunteer> volunteerSet) {
    this.volunteerSet = volunteerSet;
  }
}
