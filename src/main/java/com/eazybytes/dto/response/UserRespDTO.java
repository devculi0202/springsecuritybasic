package com.eazybytes.dto.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserRespDTO {
  private String firstName;
  private String lastName;
  private String email;
  private RoleDTO role;
}
