package com.venkat.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.venkat.entities.StudentProfile;


@Repository
public interface StudentProfileRepository extends JpaRepository<StudentProfile, Integer> {

	

	Optional<StudentProfile> findByStudentId(int studentId);

}
