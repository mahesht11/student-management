package com.student.mgmt.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentException extends RuntimeException{
    private String errorMessage;
    private Integer errorCode;
    public StudentException(String message, Integer code){
        this.errorMessage = message;
        this.errorCode = code;
    }
}
