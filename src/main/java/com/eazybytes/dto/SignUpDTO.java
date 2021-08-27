
package com.eazybytes.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpDTO {
  private String email;
  private String password;
  private String firstName;
  private String lastName;
  private String phoneNumber;
}
