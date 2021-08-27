package com.eazybytes.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.eazybytes.dto.UserDTO;
import com.eazybytes.dto.response.UserRespDTO;
import com.eazybytes.entity.User;
import com.eazybytes.exceptions.ResourceNotFoundException;
import com.eazybytes.exceptions.UnauthorizedException;
import com.eazybytes.exceptions.UserExistException;
import com.eazybytes.services.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
  @Autowired
  private UserService userService;
  
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody UserDTO userDTO) throws UserExistException {
    return ResponseEntity.status(HttpStatus.CREATED)
        .body(userService.createUser(userDTO));
  }
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @GetMapping("/{id}")
  public ResponseEntity<User> getUser(@PathVariable("id") String id)
      throws ResourceNotFoundException, UnauthorizedException {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getUser(id));
  }
  
  @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
  @GetMapping
  public ResponseEntity<UserRespDTO> getCurrentUser()
      throws ResourceNotFoundException {
    return ResponseEntity.status(HttpStatus.OK).body(userService.getCurrentUser());
  }
  
}
