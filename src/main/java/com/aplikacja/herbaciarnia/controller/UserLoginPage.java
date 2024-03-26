package com.aplikacja.herbaciarnia.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserLoginPage {

    @GetMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
    public String welcome(){
        return "Welcome!";
    }

    @GetMapping(value = "/login", produces = MediaType.APPLICATION_JSON_VALUE)
    public String login(){
        return "Hello you";
    }

    @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
    public String user(){
        return "Hello user!";
   }

    @GetMapping(value = "/admin", produces = MediaType.APPLICATION_JSON_VALUE)
    public String admin(){
        return "Hello admin !";
    }

}

