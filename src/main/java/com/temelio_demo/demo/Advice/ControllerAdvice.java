package com.temelio_demo.demo.Advice;


import com.temelio_demo.demo.Dtos.ErrorResponseDto;
import com.temelio_demo.demo.Exceptions.CustomException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class ControllerAdvice {
    @ExceptionHandler(CustomException.class)
    public ResponseEntity<Object> handleCustomException(CustomException e) {
        log.warn("CustomException occurred : {}", e.getDebugMessage());
        return generateResponse(HttpStatus.valueOf(e.getHttpStatus().value()), ErrorResponseDto.builder().errorMessage(e.getMessage()).build());
    }



    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleException(Exception e) {
        log.error(e.getClass().getSimpleName() + " Exception occurred {}", e.getMessage(), e);
        return generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponseDto.builder().errorMessage(e.getMessage()).build());
    }


    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<Object> handleRuntimeException(Exception e) {
        log.error(e.getClass().getSimpleName() + " Exception occurred {}", e.getMessage(), e);
        return generateResponse(HttpStatus.INTERNAL_SERVER_ERROR, ErrorResponseDto.builder().errorMessage("Something went wrong").build());
    }


    private ResponseEntity<Object> generateResponse(HttpStatus httpStatus, ErrorResponseDto errorResponseDTO) {
        return ResponseEntity.status(httpStatus).contentType(MediaType.APPLICATION_JSON).body(errorResponseDTO);
    }
}
