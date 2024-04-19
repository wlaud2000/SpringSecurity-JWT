package com.study.springsecurityjwt.controller;

import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping(value = "/")
    public String mainP(){

        return "main Controller";
    }
}
