package com.example.ss_2022_c4_e1.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoConrtroller {

    @GetMapping("/Demo")
    public String Demo(){
        return "Demo!";
    }
}
