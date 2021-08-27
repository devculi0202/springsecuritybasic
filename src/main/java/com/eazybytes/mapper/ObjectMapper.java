package com.eazybytes.mapper;

import org.modelmapper.ModelMapper;
import com.eazybytes.dto.DTOEntity;

public class ObjectMapper {
  private ObjectMapper() {}

  public static DTOEntity convertToDto(Object obj, DTOEntity mapper) {
    return new ModelMapper().map(obj, mapper.getClass());
  }

  public static Object convertToEntity(Object obj, DTOEntity mapper) {
    return new ModelMapper().map(mapper, obj.getClass());
  }
}
