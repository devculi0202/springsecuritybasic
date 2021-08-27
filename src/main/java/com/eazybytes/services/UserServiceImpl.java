package com.eazybytes.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import com.eazybytes.dto.UserDTO;
import com.eazybytes.dto.response.RoleDTO;
import com.eazybytes.dto.response.UserRespDTO;
import com.eazybytes.entity.User;
import com.eazybytes.entity.UserDetails;
import com.eazybytes.exceptions.ErrorCode;
import com.eazybytes.exceptions.ResourceNotFoundException;
import com.eazybytes.exceptions.UserExistException;
import com.eazybytes.mapper.ObjectMapper;
import com.eazybytes.repository.UserDetailsRepository;
import com.eazybytes.repository.UserRepository;
import com.eazybytes.security.EazybytesUserDetails;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserDetailsRepository userDetailsRepository;

  @Override
  public User getUser(String id) throws ResourceNotFoundException {
    return userRepository.findById(id).orElseThrow(
        () -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, String.valueOf(id)));
  }

  @Override
  public User createUser(UserDTO userDto) throws UserExistException {
    Optional<User> userOpt = userRepository.findByEmail(userDto.getEmail());
    if (userOpt.isPresent()) {
      log.error("Error save user {}", userDto.getEmail());
      throw new UserExistException(ErrorCode.USER_EXIST, userDto.getEmail());
    }
    User userEntity = (User) ObjectMapper.convertToEntity(new User(), userDto);
    return userRepository.save(userEntity);
  }


  @Override
  public User getUserByEmail(String email) throws ResourceNotFoundException {
    return userRepository.findByEmail(email).orElseThrow(
        () -> new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, String.valueOf(email)));
  }

  @Override
  public UserRespDTO getCurrentUser() throws ResourceNotFoundException {
    EazybytesUserDetails principal =
        (EazybytesUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    Optional<User> userOpt = userRepository.findByEmail(principal.getUsername());
    if (userOpt.isPresent()) {
      UserDetails userDetails = userDetailsRepository.findByUser(userOpt.get());
      return UserRespDTO.builder().email(userOpt.get().getEmail())
          .firstName(userDetails.getFirstName()).lastName(userDetails.getLastName())
          .role(new RoleDTO(userOpt.get().getRole().getRoleName())).build();
    }
    else
      throw new ResourceNotFoundException(ErrorCode.RESOURCE_NOT_FOUND, "User");
  }

}
