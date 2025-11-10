package com.springboot.demoSpring.service;

import com.springboot.demoSpring.DTO.StudentDto;
import com.springboot.demoSpring.entity.StudentUser;
import com.springboot.demoSpring.Mapper.StudentMapper;
import com.springboot.demoSpring.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.stream.Collectors;
@Slf4j
@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private EmailService emailService;

    public StudentDto createStudent(StudentDto dto) {
        StudentUser student = StudentMapper.toEntity(dto);
        studentRepository.save(student);
        log.info("Creating student: {}", student.getUsername());
//        if (student.getEmail() != null && !student.getEmail().isBlank()) {
//            String subject = "Welcome to Student Management System";
//            String body = "Hi " + student.getUsername() + ",\n\nYour record has been created successfully.\n\nRegards,\nAdmin";
//            emailService.sendSimpleMail(student.getEmail(), subject, body);
//        }

        return StudentMapper.toDto(student);
    }

    public List<StudentDto> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(StudentMapper::toDto)
                .collect(Collectors.toList());
    }
    public Page<StudentDto> getStudentsPaged(int page, int size) {
        log.info("Fetching students with pagination: page={}, size={}", page, size);
        Pageable pageable = PageRequest.of(page, size);
        Page<StudentUser> studentPage = studentRepository.findAll(pageable);
        return studentPage.map(StudentMapper::toDto);
    }
    public StudentDto updateStudent(Long id, StudentDto dto) {
        StudentUser student = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found"));
        log.info("Updating student: {}", student.getUsername());
        student.setUsername(dto.getUsername());
        student.setEmail(dto.getEmail());
        if(student.getEmail() != null && !student.getEmail().isBlank()){
            String subject = "Account Update Notification";
            String body = "Hi " + student.getUsername() + ",\n\nYour student record has been updated successfully.\n\nRegards,\nAdmin";
            emailService.sendSimpleMail(student.getEmail(), subject, body);
        }
        StudentUser updated = studentRepository.save(student);
        return StudentMapper.toDto(updated);
    }

    public void deleteStudent(Long id) {
        StudentUser studentUser = studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        log.info("Deleting student: {}", studentUser.getUsername());
        if(studentUser.getEmail() != null && !studentUser.getEmail().isBlank()){
            String subject = "Account Deletion Notification";
            String body = "Hi " + studentUser.getUsername() + ",\n\nYour student record has been deleted from our system.\n\nRegards,\nAdmin";
            emailService.sendSimpleMail(studentUser.getEmail(), subject, body);
        }
        studentRepository.deleteById(id);
    }
    public StudentDto getStudentById(Long id) {
        StudentUser student = studentRepository.findById(id)
                             .orElseThrow(() -> new RuntimeException("Student not found with id " + id));
        return StudentMapper.toDto(student);
    }

}
