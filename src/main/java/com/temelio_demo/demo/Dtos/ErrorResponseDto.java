package com.temelio_demo.demo.Dtos;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class ErrorResponseDto {
    private String errorMessage;
}
