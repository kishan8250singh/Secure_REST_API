package com.springboot.demoSpring.service;

import com.springboot.demoSpring.DTO.StudentDto;
import com.springboot.demoSpring.Mapper.StudentMapper;
import com.springboot.demoSpring.entity.Student;
import com.springboot.demoSpring.repository.StudentRepository;
//import org.hibernate.mapping.List;
import org.hibernate.collection.internal.StandardIdentifierBagSemantics;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository){
        this.studentRepository = studentRepository;
    }

    public StudentDto createStudent(StudentDto sdo){
        // DTO to entity
        Student student = StudentMapper.toEntity(sdo);
        // save in a database
        studentRepository.save(student);
        // entity to DTO
        return StudentMapper.toDto(student);
    }

    public List<StudentDto> getAllStudent(){
        return studentRepository
                .findAll()
                .stream()
                .map(StudentMapper::toDto).
                collect(Collectors.toList());
    }

    public StudentDto UpdateStudent(Long id , StudentDto studentDto){

        // find the student with the id given in postman calls
       Student existingstudent = studentRepository.findById(id)
               .orElseThrow(()-> new RuntimeException("Student not found") );

       // now update  the student
        existingstudent.setName(studentDto.getName());
        existingstudent.setEmail(studentDto.getEmail());

       // now save the student
         Student updatestudent = studentRepository.save(existingstudent);
         return StudentMapper.toDto(updatestudent);

    }

   public void  DeleteStudent(Long id){
   if(!studentRepository.existsById(id)){
       throw new RuntimeException("student not found ");
   }
   studentRepository.deleteById(id);
   }
   
}
