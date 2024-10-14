package com.example.swp391_fall24_be.utils;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter extends TypeAdapter<LocalTime> {
    private static final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;

    @Override
    public void write(JsonWriter jsonWriter, LocalTime localTime) throws IOException {
        if (localTime == null) {
            jsonWriter.nullValue();
            return;
        }
        jsonWriter.value(localTime.format(formatter));
    }

    @Override
    public LocalTime read(JsonReader jsonReader) throws IOException {
        return LocalTime.parse(jsonReader.nextString(), formatter);
    }
}
