package com.example.swp391_fall24_be.core;

import lombok.Data;

enum ErrorType {
    EntityNotFound,
    ValidationError,

}
@Data
public class ErrorReport{
    private String functionName;
    private ErrorType errorType;
    private String message;

    public ErrorReport( String functionName, ErrorType errorType, String message) {
        this.functionName = functionName;
        this.errorType = errorType;
        this.message = message;
    }
//    public String getFunctionName() {
//        return functionName;
//    }
//
//    public void setFunctionName(String functionName) {
//        this.functionName = functionName;
//    }
//
//    public ErrorType getErrorType() {
//        return errorType;
//    }
//
//    public void setErrorType(ErrorType errorType) {
//        this.errorType = errorType;
//    }
//
//    public String getMessage() {
//        return message;
//    }
//
//    public void setMessage(String message) {
//        this.message = message;
//    }
}
