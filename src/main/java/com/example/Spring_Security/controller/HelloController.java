package com.example.Spring_Security.controller;

import org.springframework.web.bind.annotation.*;

import java.util.Scanner;

@RestController
@RequestMapping("/happy")
public class HelloController {

    @GetMapping("hi")
    public String hello(){
        return "hello rey";
    }

    @GetMapping("/bye")
    public String bye(){
        return "bye";
    }

   @PostMapping("/message")
    public String message(@RequestBody  String message){
        return message;
   }
}
