package com.eazybytes.exceptions;

public enum ErrorCode {
  RESOURCE_NOT_FOUND("Could not find resource: "),
  USER_EXIST("User does exist on database: ");

  private String reason;

  private ErrorCode(String reason) {
    this.reason = reason;
  }

  public String getReason() {
    return reason;
  }

}
