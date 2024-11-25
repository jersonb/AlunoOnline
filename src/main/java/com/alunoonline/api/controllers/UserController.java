package com.alunoonline.api.controllers;

import com.alunoonline.api.configs.JwtService;
import com.alunoonline.api.services.UserInfoService;
import com.alunoonline.api.viewobjects.requests.AuthRequest;
import com.alunoonline.api.viewobjects.requests.CreateUserRequest;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@RequestMapping("/users")
@Tag(name = "Usuários", description = "Gerencie os usuários")
@RestControllerAdvice
public class UserController {

    final UserInfoService service;
    final JwtService jwtService;
    final AuthenticationManager authenticationManager;

    public UserController(UserInfoService service, JwtService jwtService, AuthenticationManager authenticationManager) {
        this.service = service;
        this.jwtService = jwtService;
        this.authenticationManager = authenticationManager;
    }


    @Operation(summary = "Cria um usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "201", description = "Usuário criado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<String> create(@Valid @RequestBody CreateUserRequest request) {

        var user = request.toEntity();
        var created = service.addUser(user);
        if (created) {
            var location = ServletUriComponentsBuilder.fromCurrentRequest().path("/auth").buildAndExpand().toUri();
            return ResponseEntity.created(location).build();
        }

        return new ResponseEntity<>("Problemas ao cadastrar usuário, favor verificar na secretaria", HttpStatus.BAD_REQUEST);

    }

    @Operation(summary = "Login de um usuário", method = "POST")
    @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Usuário Logado"), @ApiResponse(responseCode = "500", description = "Erro inesperado"),})
    @PostMapping("/auth")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<String> auth(@Valid @RequestBody AuthRequest request) {
        var userAuthentication = new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword());
        var authentication = authenticationManager.authenticate(userAuthentication);
        if (authentication.isAuthenticated()) {
            var token = jwtService.generateToken(request.getUsername());
            return ResponseEntity.ok(token);
        }
        throw new UsernameNotFoundException("Invalid user request!");


    }
}
