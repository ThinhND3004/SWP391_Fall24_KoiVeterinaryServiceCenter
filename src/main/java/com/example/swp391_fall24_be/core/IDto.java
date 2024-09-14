package com.example.swp391_fall24_be.core;

public interface IDto<EntityType> {
    EntityType toEntity();
}
