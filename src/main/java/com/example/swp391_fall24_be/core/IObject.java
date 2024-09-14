package com.example.swp391_fall24_be.core;

public interface IObject<EntityType> {
    EntityType toResponseDto();
}
