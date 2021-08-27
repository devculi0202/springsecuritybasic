package com.eazybytes.security;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import com.eazybytes.entity.User;
import com.eazybytes.repository.UserRepository;

@Service
public class EazybytesUserDetailsService implements UserDetailsService {

  @Autowired
  private UserRepository userRepository;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> userOpt = userRepository.findByEmail(username);
    if (userOpt.isPresent()) {
      return new EazybytesUserDetails(userOpt.get());
    } else {
      throw new UsernameNotFoundException("User details not found for the user : " + username);
    }
  }
}
