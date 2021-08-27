package com.eazybytes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.eazybytes.utils.StringUtils;

@ResponseStatus(code = HttpStatus.CONFLICT)
public class UserExistException extends Exception {
  private static final long serialVersionUID = 1L;

  public UserExistException(ErrorCode errorCode, String resource) {
    super(StringUtils.joinStrings(errorCode.getReason(), resource));
  }
  
  public HttpStatus getStatus() {
    return HttpStatus.CONFLICT;
  }

}
