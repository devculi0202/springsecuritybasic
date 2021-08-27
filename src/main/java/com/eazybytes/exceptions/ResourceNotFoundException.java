package com.eazybytes.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
import com.eazybytes.utils.StringUtils;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends Exception {
  private static final long serialVersionUID = 1L;

  public ResourceNotFoundException(ErrorCode errorCode, String resource) {
    super(StringUtils.joinStrings(errorCode.getReason(), resource));
  }
  
  public HttpStatus getStatus() {
    return HttpStatus.NOT_FOUND;
  }

}
