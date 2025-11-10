package com.springboot.demoSpring.mapper;

import com.springboot.demoSpring.DTO.StudentDto;
import com.springboot.demoSpring.entity.StudentUser;

public class StudentMapper {

    public static StudentUser toEntity(StudentDto dto) {
        StudentUser user = new StudentUser();
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        return user;
    }

    public static StudentDto toDto(StudentUser user) {
        return new StudentDto(user.getUsername(), user.getEmail());
    }
}
