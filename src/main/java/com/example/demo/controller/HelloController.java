package com.example.demo.controller;

import com.example.demo.configuration.HelloConfiguration;
import com.example.demo.domain.User;
import com.example.demo.service.HelloWorldService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    private HelloWorldService helloWorldService;

    @Autowired
    private HelloConfiguration helloConfiguration;

    public HelloController(HelloWorldService helloWorldService) {
        this.helloWorldService = helloWorldService;
    }

    @GetMapping
    public String helloMessage() {
        return helloWorldService.helloWorld("Walmir");
    }

    @PostMapping("/{id}")
        public String helloMessagePost(@PathVariable("id") String id, @RequestParam(value = "filter", defaultValue = "nenhum") String filter, @RequestBody User body) {
        return "Hello, world post!" + body.getName() + id + filter;
    }
}
