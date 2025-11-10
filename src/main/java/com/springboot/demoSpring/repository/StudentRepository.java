package com.springboot.demoSpring.repository;

import com.springboot.demoSpring.entity.StudentUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<StudentUser,Long>{

}
