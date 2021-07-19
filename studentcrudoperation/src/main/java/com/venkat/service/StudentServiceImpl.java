package com.venkat.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.PathVariable;

import com.venkat.entities.Course;
import com.venkat.entities.Student;
import com.venkat.entities.StudentProfile;




public interface StudentServiceImpl 
{
	Student createStudent(Student student);
	Student updateStudent(int id, Student student);
	List<Student> getAllStudent();
	Student getStudentById(int id);
	void deleteStudent(int id);
	List<StudentProfile> getAllStudentProfile();
	StudentProfile getStudentProfileById(int id);
	StudentProfile updateStudentProfile(int studentId, @Valid StudentProfile profile);
	public Course getCourseById(@PathVariable int id);
	Course updateCourse(int courserId, Course course);
	List<Course> getCourses();
	List<Course> getAllCoursesMappedByStudentId(int id);
	List<Course> updateCoursesMappedByStudentId(int id, List<Course> student);
	StudentProfile getStudentProfileMappedByStudentId(int studentId);
	

}
