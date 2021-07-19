package com.venkat.controller;

import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.venkat.dtos.StudentDto;
import com.venkat.entities.Course;
import com.venkat.entities.Student;
import com.venkat.entities.StudentProfile;
import com.venkat.service.StudentService;

@RestController
public class StudentController {

	@Autowired
	private StudentService studentService;

	Logger log = LoggerFactory.getLogger(StudentController.class);

	@GetMapping("/students")
	public ResponseEntity<List<Student>> getAllStudent() {
		return ResponseEntity.ok().body(studentService.getAllStudent());

	}

	@GetMapping("/students/{id}")
	public ResponseEntity<Student> getStudentById(@PathVariable int id) {
		return ResponseEntity.ok().body(studentService.getStudentById(id));

	}

	@PostMapping("/students")
	public ResponseEntity<Student> createStudent(@Valid @RequestBody Student student) {
		return ResponseEntity.ok().body(studentService.createStudent(student));

	}

	@PutMapping("/students/{id}")
	public ResponseEntity<String> updateStudent(@PathVariable int id, @Valid @RequestBody Student student) {
		student.setId(id);
		this.studentService.updateStudent(id, student);
		return ResponseEntity.ok().body("Student is updated");

	}

	@DeleteMapping("/students/{id}")
	public String deleteStudent(@PathVariable int id) {
		this.studentService.deleteStudent(id);
		return "Student is Deleted..!";
	}

	/* Controller for student-profile one to one mapping */

	@PutMapping("/students/{id}/student-profiles")
	public ResponseEntity<String> updateStudentProfile(@PathVariable("id") int studentId,
			@Valid @RequestBody StudentProfile profile) {
		this.studentService.updateStudentProfile(studentId, profile);
		return ResponseEntity.ok().body("Student profile is Updated ");
	}

	@GetMapping("/student-profiles/{id}")
	public ResponseEntity<StudentProfile> getStudentProfileById(@PathVariable int id) {
		return ResponseEntity.ok().body(studentService.getStudentProfileById(id));
	}

	@GetMapping("/student-profiles")
	public ResponseEntity<List<StudentProfile>> getAllStudentProfile() {
		return ResponseEntity.ok().body(studentService.getAllStudentProfile());
	}

	@GetMapping("/students/{id}/student-profiles")
	public ResponseEntity<StudentProfile> getStudentProfileMappedByStudentId(@PathVariable("id") int studentId) {
		return ResponseEntity.ok().body(this.studentService.getStudentProfileMappedByStudentId(studentId));

	}

	/* Controller for courses one to many mapping */

	@PutMapping("/courses/{id}")
	public ResponseEntity<Course> updateCourse(@PathVariable int id, @Valid @RequestBody Course course) {
		return ResponseEntity.ok().body(this.studentService.updateCourse(id, course));
	}

	@GetMapping("/courses/{id}")
	public ResponseEntity<Course> getCourseById(@PathVariable int id) {
		return ResponseEntity.ok().body(this.studentService.getCourseById(id));
	}

	@GetMapping("/courses")
	public ResponseEntity<List<Course>> getCourses() {
		return ResponseEntity.ok().body(studentService.getCourses());
	}

	@GetMapping("/students/{id}/courses")
	public ResponseEntity<List<Course>> getAllCoursesMappedByStudentId(@PathVariable("id") int studentId) {
		return ResponseEntity.ok().body(this.studentService.getAllCoursesMappedByStudentId(studentId));

	}

	@PutMapping("/students/{id}/courses")
	public ResponseEntity<String> updateCoursesMappedByStudentId(@PathVariable int id,
			@RequestBody List<Course> student) {
		this.studentService.updateCoursesMappedByStudentId(id, student);
		return ResponseEntity.ok().body("Courses are updated mapped by student id " + id);

	}

	/* Query parameter */

	@GetMapping("/students/courses")
	public ResponseEntity<List<Course>> getAllCoursesMappedByStudentIdByQuery(
			@RequestParam(value = "id") int studentId) {
		return ResponseEntity.ok().body(this.studentService.getAllCoursesMappedByStudentId(studentId));

	}
	
	
//	@GetMapping("/students/{id}/courses/{courseId}")
//	public ResponseEntity<Course> getAllCoursesMappedByStudentIdBy(@PathVariable("id") int id,@PathVariable int courseId ) {
//		return ResponseEntity.ok().body(this.studentService.getAllCoursesMappedByStudentIdBy(id, courseId));
//
//	}

}
