package com.springboot.demoSpring.Controller;

import com.springboot.demoSpring.DTO.StudentDto;
import com.springboot.demoSpring.service.StudentService;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("api/student")
public class StudentController {
    private final StudentService studentService;
    public StudentController(StudentService studentService){
        this.studentService = studentService;
    }
    @PostMapping
    public StudentDto create_Student(@RequestBody StudentDto studentDto){
        return studentService.createStudent(studentDto);
    }
    @GetMapping
    public List<StudentDto> GetAll_Student(){
        return studentService.getAllStudent();
    }
    @PutMapping("/{id}")
    public StudentDto update_Student(@PathVariable Long id, @RequestBody StudentDto studentDto){
        return studentService.UpdateStudent(id,studentDto);
    }
    @DeleteMapping("/{id}")
    public String delete_Student(@PathVariable Long id){
        studentService.DeleteStudent(id);
        return "Student with id "+id+ "deleted successfully !" ;
    }
}
