package com.example.swp391_fall24_be.core;


import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class ResponseDto<EntityType>{
    public HttpStatus status;
    public String message;
    public EntityType data;
    public String err;

    public ResponseDto() {
    }

    public ResponseDto(HttpStatus status, String message, EntityType data, String err) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.err = err;
    }
}