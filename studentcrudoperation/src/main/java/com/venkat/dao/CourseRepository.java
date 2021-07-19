package com.venkat.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.venkat.entities.Course;

public interface CourseRepository extends JpaRepository<Course, Integer> {

	

}
