package com.eazybytes.services;

import com.eazybytes.dto.UserDTO;
import com.eazybytes.dto.response.UserRespDTO;
import com.eazybytes.entity.User;
import com.eazybytes.exceptions.ResourceNotFoundException;
import com.eazybytes.exceptions.UserExistException;

public interface UserService {
  User getUser(String id) throws ResourceNotFoundException;
  
  User getUserByEmail(String email) throws ResourceNotFoundException;

  User createUser(UserDTO userDto) throws UserExistException;

  UserRespDTO getCurrentUser() throws ResourceNotFoundException;
}
