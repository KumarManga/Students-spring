package com.venkat.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.venkat.dtos.CourseDto;
import com.venkat.dtos.StudentDto;
import com.venkat.dtos.StudentProfileDto;
import com.venkat.service.StudentService;
import com.venkat.service.StudentServiceDto;

@RestController
public class StudentControllerDto {

	@Autowired
	private StudentServiceDto studentServiceDto;

	@Autowired
	private StudentService studentService;

	@GetMapping("/student")
	public List<StudentDto> getAllStudent() {
		return studentServiceDto.getAllStudent();

	}

	@GetMapping("/student/{id}")
	public ResponseEntity<StudentDto> getStudentById(@PathVariable int id) {
		return ResponseEntity.ok().body(studentServiceDto.getStudentById(id));

	}

	@PostMapping("/student")
	public ResponseEntity<StudentDto> createStudent(@Valid @RequestBody StudentDto studentDto) {
		return ResponseEntity.ok().body(studentServiceDto.createStudent(studentDto));

	}

	@DeleteMapping("/student/{id}")
	public String deleteStudent(@PathVariable int id) {
		this.studentService.deleteStudent(id);
		return "Student is Deleted..!";
	}

	/* Controller for student-profileDto one to one mapping */

	@GetMapping("/student-profile/{id}")
	public ResponseEntity<StudentProfileDto> getStudentProfileById(@PathVariable int id) {
		return ResponseEntity.ok().body(studentServiceDto.getStudentProfileById(id));
	}

	@GetMapping("/student-profile")
	public ResponseEntity<List<StudentProfileDto>> getAllStudentProfile() {
		return ResponseEntity.ok().body(studentServiceDto.getAllStudentProfile());
	}

	@GetMapping("/student/{id}/student-profile")
	public ResponseEntity<StudentProfileDto> getStudentProfileMappedByStudentId(@PathVariable("id") int studentId) {
		return ResponseEntity.ok().body(this.studentServiceDto.getStudentProfileMappedByStudentId(studentId));

	}

	/* Controller for courseDto one to many mapping */

	@GetMapping("/course/{id}")
	public ResponseEntity<CourseDto> getCourseById(@PathVariable int id) {
		return ResponseEntity.ok().body(this.studentServiceDto.getCourseById(id));
	}

	@GetMapping("/course")
	public ResponseEntity<List<CourseDto>> getCourses() {
		return ResponseEntity.ok().body(studentServiceDto.getCourses());
	}

	@GetMapping("/student/{id}/course")
	public ResponseEntity<List<CourseDto>> getAllCoursesMappedByStudentId(@PathVariable("id") int studentId) {
		return ResponseEntity.ok().body(this.studentServiceDto.getAllCoursesMappedByStudentId(studentId));

	}

}
