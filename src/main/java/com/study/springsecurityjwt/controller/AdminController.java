package com.study.springsecurityjwt.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AdminController {

    @GetMapping(value = "/admin")
    public String adminp(){

        return "admin Controller";
    }
}
