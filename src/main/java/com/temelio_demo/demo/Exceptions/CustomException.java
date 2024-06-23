package com.temelio_demo.demo.Exceptions;

import lombok.Data;
import org.springframework.http.HttpStatus;


@Data
public class CustomException extends Exception{
    final HttpStatus httpStatus;
    final String message;
    final String debugMessage;
    public CustomException(HttpStatus httpStatus,String message, String debugMessage) {
        this.httpStatus=httpStatus;
        this.message=message;
        this.debugMessage=debugMessage;
    }
}
