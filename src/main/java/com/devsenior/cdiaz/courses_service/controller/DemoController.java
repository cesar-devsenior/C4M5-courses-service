package com.devsenior.cdiaz.courses_service.controller;

import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/demo")
public class DemoController {
    
    @GetMapping
    public Map<String,Object> getData() {
        return Map.of("message","Hola Mundo!");
    }
    
}
