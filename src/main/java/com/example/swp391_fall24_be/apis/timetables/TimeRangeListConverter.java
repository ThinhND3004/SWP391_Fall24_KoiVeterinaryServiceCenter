package com.example.swp391_fall24_be.apis.timetables;

import com.fasterxml.jackson.core.type.TypeReference;
import com.example.swp391_fall24_be.sub_class.TimeRange;
//import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

//import javax.persistence.AttributeConverter;
//import javax.persistence.Converter;
import java.util.ArrayList;
import java.util.List;

@Converter
public class TimeRangeListConverter implements AttributeConverter<List<TimeRange>, String> {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public String convertToDatabaseColumn(List<TimeRange> timeRanges) {
        try {
            return objectMapper.writeValueAsString(timeRanges);
        } catch (Exception e) {
            return "[]"; // Trả về danh sách rỗng nếu có lỗi
        }
    }

    @Override
    public List<TimeRange> convertToEntityAttribute(String dbData) {
        try {
            return objectMapper.readValue(dbData,
                    new TypeReference<List<TimeRange>>() {});
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }
}
