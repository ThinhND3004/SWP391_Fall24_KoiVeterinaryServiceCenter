package com.example.swp391_fall24_be.core;

public class ResponseDto<EntityType>{
    public int status;
    public String message;
    public EntityType data;
    public String err;

    public ResponseDto() {
    }

    public ResponseDto(int status, String message, String err) {
        this.status = status;
        this.message = message;
        this.err = err;
    }

    public ResponseDto(int status, String message, EntityType data, String err) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.err = err;
    }
}
