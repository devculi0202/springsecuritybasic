package com.eazybytes.services;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.eazybytes.dto.LoginDTO;
import com.eazybytes.dto.SignUpDTO;
import com.eazybytes.dto.response.SignUpRespDTO;
import com.eazybytes.entity.Roles;
import com.eazybytes.entity.User;
import com.eazybytes.entity.UserDetails;
import com.eazybytes.enums.UserRole;
import com.eazybytes.exceptions.ErrorCode;
import com.eazybytes.exceptions.ResourceNotFoundException;
import com.eazybytes.exceptions.UserExistException;
import com.eazybytes.repository.UserDetailsRepository;
import com.eazybytes.repository.UserRepository;
import com.eazybytes.security.EazybytesUserDetails;
import com.eazybytes.security.JwtTokenProvider;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AuthServiceImpl implements AuthService {
  @Autowired
  private AuthenticationManager authenticationManager;

  @Autowired
  private PasswordEncoder passwordEncoder;

  @Autowired
  private UserRepository userRepository;

  @Autowired
  private UserDetailsRepository userDetailsRepository;

  @Autowired
  private JwtTokenProvider tokenProvider;

  @Autowired
  private UserService userService;

  @Override
  public String login(LoginDTO loginDTO) throws ResourceNotFoundException {
    log.info("User {} login ", loginDTO.getEmail());
    Authentication authentication = authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword()));
    EazybytesUserDetails userDetails = (EazybytesUserDetails) authentication.getPrincipal();
    User user = userService.getUserByEmail(userDetails.getUsername());
    return tokenProvider.createToken(user.getId(), user.getEmail());
  }

  @Override
  public SignUpRespDTO signUp(SignUpDTO signUpDTO) throws UserExistException {
    Optional<User> userOpt = userRepository.findByEmail(signUpDTO.getEmail());
    if (userOpt.isPresent()) {
      throw new UserExistException(ErrorCode.USER_EXIST, signUpDTO.getEmail());
    }
    UserDetails userInfoBuilder = UserDetails.builder().firstName(signUpDTO.getFirstName())
        .lastName(signUpDTO.getLastName()).user(new User(signUpDTO.getEmail(),
            passwordEncoder.encode(signUpDTO.getPassword()), new Roles(UserRole.ROLE_USER.name())))
        .phoneNumber(signUpDTO.getPhoneNumber()).build();
    UserDetails userDetails = userDetailsRepository.save(userInfoBuilder);
    return SignUpRespDTO.builder().email(userDetails.getUser().getEmail())
        .firstName(userDetails.getFirstName()).lastName(userDetails.getLastName())
        .role(userDetails.getUser().getRole()).build();
  }
}
