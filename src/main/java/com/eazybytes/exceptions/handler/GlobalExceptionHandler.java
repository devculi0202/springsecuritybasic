package com.eazybytes.exceptions.handler;

import java.io.IOException;
import java.time.LocalDateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import com.eazybytes.dto.error.ErrorDTO;
import com.eazybytes.exceptions.UnauthorizedException;
import com.eazybytes.exceptions.ResourceNotFoundException;
import com.eazybytes.exceptions.UserExistException;

@ControllerAdvice
public class GlobalExceptionHandler {
  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<ErrorDTO> generateNotFoundException(ResourceNotFoundException ex) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setMessage(ex.getMessage());
    errorDTO.setStatus(String.valueOf(ex.getStatus().value()));
    errorDTO.setTime(LocalDateTime.now());
    return new ResponseEntity<>(errorDTO, ex.getStatus());
  }
  
  @ExceptionHandler(UserExistException.class)
  public ResponseEntity<ErrorDTO> generateExistException(UserExistException ex) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setMessage(ex.getMessage());
    errorDTO.setStatus(String.valueOf(ex.getStatus().value()));
    errorDTO.setTime(LocalDateTime.now());
    return new ResponseEntity<>(errorDTO, ex.getStatus());
  }
  
  @ExceptionHandler(UnauthorizedException.class)
  public ResponseEntity<ErrorDTO> handleUnauthorizedException(UnauthorizedException ex) throws IOException{
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setMessage(ex.getMessage());
    errorDTO.setStatus(String.valueOf(HttpStatus.UNAUTHORIZED.value()));
    errorDTO.setTime(LocalDateTime.now());
    return new ResponseEntity<>(errorDTO, HttpStatus.UNAUTHORIZED);
  }
  
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorDTO> handleValidationExceptions(MethodArgumentNotValidException ex) {
    ErrorDTO errorDTO = new ErrorDTO();
    errorDTO.setMessage(ex.getMessage());
    errorDTO.setStatus(String.valueOf(HttpStatus.BAD_GATEWAY.value()));
    errorDTO.setTime(LocalDateTime.now());
    return new ResponseEntity<>(errorDTO, HttpStatus.BAD_GATEWAY);
  }
}
