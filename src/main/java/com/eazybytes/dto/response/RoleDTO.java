package com.eazybytes.dto.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RoleDTO {
  
  private String roleName;

  public RoleDTO(String roleName) {
    this.roleName = roleName;
  }
  
}
