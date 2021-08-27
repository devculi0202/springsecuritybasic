package com.eazybytes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eazybytes.dto.LoginDTO;
import com.eazybytes.dto.SignUpDTO;
import com.eazybytes.dto.response.SignUpRespDTO;
import com.eazybytes.exceptions.ResourceNotFoundException;
import com.eazybytes.exceptions.UserExistException;
import com.eazybytes.services.AuthService;

@RestController
@RequestMapping("/user")
public class AuthController {
  @Autowired
  private AuthService authService;

  @PostMapping(value = "/signin", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<String> login(@RequestBody LoginDTO loginDTO) throws ResourceNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(authService.login(loginDTO));
  }
  
  @PostMapping(value = "/signup", consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<SignUpRespDTO> registerAccount(@RequestBody SignUpDTO signUpDTO)
      throws UserExistException {
    return ResponseEntity.status(HttpStatus.OK).body(authService.signUp(signUpDTO));
  }

}
