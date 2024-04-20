package com.study.springsecurityjwt.controller;

import com.study.springsecurityjwt.dto.JoinDTO;
import com.study.springsecurityjwt.service.JoinService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class JoinController {

    private final JoinService joinService;

    @PostMapping(value = "/join")
    public String joinProcess(@RequestBody JoinDTO joinDTO){

        joinService.joinProcess(joinDTO);

        return "ok";
    }
}
