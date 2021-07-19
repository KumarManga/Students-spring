package com.venkat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venkat.entities.Student;

public interface StudentRepository extends JpaRepository<Student, Integer> {

}