package com.eazybytes.dto.response;

import com.eazybytes.entity.Roles;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class SignUpRespDTO {
  private String firstName;
  private String lastName;
  private String email;
  private Roles role;
}
