package com.cherrytable.backend.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewLoginController {

  @GetMapping("/")
  public String home() {
    return "login";
  }
}
