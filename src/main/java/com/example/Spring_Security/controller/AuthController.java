package com.example.Spring_Security.controller;


import com.example.Spring_Security.entity.Role;
import com.example.Spring_Security.entity.Users;
import com.example.Spring_Security.service.UserService;
import com.example.Spring_Security.util.JWTUtil;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {


//    @Autowired
//    private UserDetailsRepository userDetailsRepository;


    private final PasswordEncoder passwordEncoder;


    private final AuthenticationManager authenticationManager;


    private final JWTUtil jwtUtil;


    private final UserService userService;

    public AuthController(PasswordEncoder passwordEncoder,
                          AuthenticationManager authenticationManager,
                          JWTUtil jwtUtil,
                          UserService userService) {
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
    }

    @PostMapping("/register")
    public String register(@RequestBody Users user){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if(user.getRole() == null){
            user.setRole(Role.USER);
        }
        userService.save(user);
      //  userDetailsRepository.save(user);
        return "User registered successfully";
    }

    @PostMapping("/authenticate")
    public String generateToken(@RequestBody Users user){
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword())
            );

            return jwtUtil.generateToken(user.getUsername());
        } catch (AuthenticationException e) {
            throw new RuntimeException(e);
        }
    }
}
