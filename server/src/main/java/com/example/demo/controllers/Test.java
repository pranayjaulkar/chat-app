package com.example.demo.controllers;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
public class Test {
    @GetMapping("/")
    public RedirectView home(){
        return new RedirectView("/api/hello");
    }
    @GetMapping("/api/hello")
    public String sayHello(){
        return "<h1>Hello world</h1>";
    }

    @PostMapping("/api/hello")
    public String sayHelloPost(){
        return "<h1>Hello world from post</h1>";
    }
}