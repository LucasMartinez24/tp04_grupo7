package ar.edu.unju.fi.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MenuController {
  @GetMapping ({"/index","/Menu","/"})
  public String getmenu(){
      return "index";
  }
  @GetMapping({"/login","/ingreso"})
  public String getlogin(){
    return "login";
  }
}
