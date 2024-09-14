package com.example.swp391_fall24_be.core;

import lombok.Data;

@Data
public class ErrorReport{
    private String functionName;
    private ErrorEnum errorType;
    private String message;

    public ErrorReport(String functionName, ErrorEnum errorType, String message) {
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
