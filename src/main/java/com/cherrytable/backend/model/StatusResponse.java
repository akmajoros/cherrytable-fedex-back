package com.cherrytable.backend.model;

import org.springframework.stereotype.Component;

@Component
public class StatusResponse implements ResponseType{

  private String message;

  public StatusResponse() {
  }

  public StatusResponse(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }
}
