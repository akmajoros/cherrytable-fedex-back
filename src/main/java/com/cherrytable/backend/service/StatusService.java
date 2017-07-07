package com.cherrytable.backend.service;

import com.cherrytable.backend.model.StatusResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

@Service
public class StatusService {

  public StatusResponse responseToMissingParameters(BindingResult bindingResult){
    List<String> missing = new ArrayList<>();
    String missingFields = new String();
    for (FieldError fielderror : bindingResult.getFieldErrors()) {
      missing.add(fielderror.getField());
    }
    missingFields = "Missing parameter(s): " + missing.stream().collect(Collectors.joining(", ")) + "!";
    return new StatusResponse(missingFields);
  }

  public StatusResponse getStatusResponse(String message) {
    StatusResponse statusResponse = new StatusResponse(message);
    return statusResponse;
  }

  public StatusResponse responseToIncorrectOrgLogin(){
    return getStatusResponse("Login id doesn't exist");
  }

  public StatusResponse responseToIncorrectPassword() {
    return getStatusResponse("Password incorrect");
  }
  public StatusResponse getOccupiedOrganisationLoginStatus() {
    return getStatusResponse("Organisation name occupied!");
  }

  public StatusResponse getRegistrationSuccessStatus() {
    return getStatusResponse("Registration was succesful!");
  }

  public StatusResponse responseToNotExistsingOrganisation(){
    return new StatusResponse("This organisation doesn't exist");
  }
}
