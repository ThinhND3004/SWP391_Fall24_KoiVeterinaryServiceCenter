package com.example.swp391_fall24_be.core;

import lombok.Data;

import java.util.List;

@Data
public class ResponseDto<EntityType>{
    public int status;
    public String message;
    public EntityType data;
    public List<String> err;

    public ResponseDto() {
    }

    public ResponseDto(int status, String message, EntityType data, List<String> err) {
        this.status = status;
        this.message = message;
        this.data = data;
        this.err = err;
    }
}
