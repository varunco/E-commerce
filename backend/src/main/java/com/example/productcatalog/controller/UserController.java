package com.example.productcatalog.controller;

import com.example.productcatalog.model.AuthRequest;
import com.example.productcatalog.model.AuthResponse;
import com.example.productcatalog.model.User;
import com.example.productcatalog.service.JwtService;
import com.example.productcatalog.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    private final UserService userService;
    private final JwtService jwtService;

    public UserController(
            UserService userService,
            JwtService jwtService
    ) {
        this.userService = userService;
        this.jwtService = jwtService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(
            @RequestBody User user
    ) {

        try {

            User registeredUser = userService.register(user);

            // Never return the password to the client
            registeredUser.setPassword(null);

            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(registeredUser);

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Registration failed: " + e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(
            @RequestBody AuthRequest request
    ) {

        try {

            User user = userService.findByEmail(
                    request.getEmail()
            );

            boolean passwordMatches =
                    userService.checkPassword(
                            request.getPassword(),
                            user.getPassword()
                    );

            if (!passwordMatches) {

                return ResponseEntity
                        .status(HttpStatus.UNAUTHORIZED)
                        .body("Invalid email or password");
            }

            String token =
                    jwtService.generateToken(user.getEmail());

            return ResponseEntity.ok(
                    new AuthResponse(token)
            );

        } catch (Exception e) {

            return ResponseEntity
                    .status(HttpStatus.UNAUTHORIZED)
                    .body("Invalid email or password");
        }
    }
}