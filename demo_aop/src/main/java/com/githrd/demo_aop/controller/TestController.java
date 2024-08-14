package com.githrd.demo_aop.controller;


import com.githrd.demo_aop.service.TestService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class TestController {

    private final TestService testService;


    @GetMapping("/hello")
    public String hello() {

        return testService.hello();
    }

    @GetMapping("/hi")
    public String hi() {

        return testService.hi();
    }

}
