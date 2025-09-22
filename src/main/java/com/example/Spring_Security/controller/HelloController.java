package com.example.Spring_Security.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/happy")
public class HelloController {


    @PreAuthorize("hasAnyRole('ADMIN' , 'USER')")
    @GetMapping("hi")
    public String hello(){
        return "hello rey";
    }

    @PreAuthorize("hasRole('ADMIN' )")
    @GetMapping("/bye")
    public String bye(){
        return "bye";
    }

    @PreAuthorize("hasRole('USER')")
   @PostMapping("/message")
    public String message(@RequestBody  String message){
        return message;
   }
}
