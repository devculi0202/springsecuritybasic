package com.eazybytes.services;

import com.eazybytes.dto.LoginDTO;
import com.eazybytes.dto.SignUpDTO;
import com.eazybytes.dto.response.SignUpRespDTO;
import com.eazybytes.exceptions.ResourceNotFoundException;
import com.eazybytes.exceptions.UserExistException;

public interface AuthService {
  String login(LoginDTO loginDTO) throws ResourceNotFoundException;

  SignUpRespDTO signUp(SignUpDTO signUpDTO) throws UserExistException;
}
