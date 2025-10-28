package com.springboot.demoSpring.Mapper;

import com.springboot.demoSpring.DTO.StudentDto;
import com.springboot.demoSpring.entity.Student;

public class StudentMapper {
    public static StudentDto toDto(Student student){
        StudentDto dto = new StudentDto();
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        return dto;
    }

    public  static Student toEntity(StudentDto dto){
        Student std = new Student();
        std.setEmail(dto.getEmail());
        std.setName(dto.getName());
        return std;
    }
}
