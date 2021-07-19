package com.venkat.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venkat.Exception.StudentResourceNotFoundException;
import com.venkat.dao.CourseRepository;
import com.venkat.dao.StudentProfileRepository;
import com.venkat.dao.StudentRepository;
import com.venkat.dtos.CourseDto;
import com.venkat.dtos.StudentDto;
import com.venkat.dtos.StudentProfileDto;
import com.venkat.entities.Course;
import com.venkat.entities.Student;
import com.venkat.entities.StudentProfile;

@Service
public class StudentServiceDto {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentProfileRepository studentProfileRepository;

	@Autowired
	private CourseRepository courseRepository;

	ModelMapper modelMapper = new ModelMapper();

	public List<StudentDto> getAllStudent() {

		List<Student> students = studentRepository.findAll();
		if (students.isEmpty()) {
			throw new StudentResourceNotFoundException("Record not found ");
		} else {
			// convert entity to DTO
			List<StudentDto> studentResponse = students.stream()
					.map(student -> modelMapper.map(student, StudentDto.class)).collect(Collectors.toList());
			return studentResponse;
		}
	}

	public StudentDto getStudentById(int id) {
		Optional<Student> studentDb = this.studentRepository.findById(id).map(student -> {
			student.getCourse();
			return student;
		});

		if (studentDb.isPresent()) {
			Student student = new Student();

			student = studentDb.get();

			// convert entity to DTO
			StudentDto studentResponse = modelMapper.map(student, StudentDto.class);
			return studentResponse;
		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + id);
		}

	}

	public StudentDto createStudent(@Valid StudentDto studentDto) {

		// convert DTO to entity
		Student studentRequest = modelMapper.map(studentDto, Student.class);

		studentRepository.save(studentRequest);

		// convert entity to DTO

		StudentDto studentResponse = modelMapper.map(studentRequest, StudentDto.class);

		return studentResponse;

	}

	public void deleteStudent(int id) {
		Optional<Student> studentDb = this.studentRepository.findById(id);

		if (studentDb.isPresent()) {
			this.studentRepository.delete(studentDb.get());
		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + id);
		}

	}

	/* service Implentation for Student-profileDto */

	public StudentProfileDto getStudentProfileById(int id) {
		Optional<StudentProfile> studentDb = this.studentProfileRepository.findById(id);
		if (studentDb.isPresent()) {

			StudentProfile studentProfile = new StudentProfile();
			studentProfile = studentDb.get();

			// convert entity to DTO
			StudentProfileDto studentResponse = modelMapper.map(studentProfile, StudentProfileDto.class);

			return studentResponse;
		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + id);

		}
	}

	public List<StudentProfileDto> getAllStudentProfile() {

		List<StudentProfile> students = studentProfileRepository.findAll();
		if (students.isEmpty()) {
			throw new StudentResourceNotFoundException("Record not found ");
		} else {
			// convert entity to DTO
			List<StudentProfileDto> studentResponse = students.stream()
					.map(student -> modelMapper.map(student, StudentProfileDto.class)).collect(Collectors.toList());
			return studentResponse;
		}
	}

	public StudentProfileDto getStudentProfileMappedByStudentId(int studentId) {
		Optional<Student> studentDb = this.studentRepository.findById(studentId);
		if (studentDb.isPresent()) {
			Student studentUpdate = studentDb.get();
			StudentProfile studentProfile = new StudentProfile();
			studentProfile = studentUpdate.getStudentProfile();

			StudentProfileDto studentResponse = modelMapper.map(studentProfile, StudentProfileDto.class);

			return studentResponse;

		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + studentId);

		}
	}

//	Service implementation for courses
	
	public CourseDto getCourseById(int id) {
		Optional<Course> courseDb = this.courseRepository.findById(id);
		if (courseDb.isPresent()) {
			Course course = new Course();
			course = courseDb.get();
			CourseDto studentResponse = modelMapper.map(course, CourseDto.class);
			return studentResponse;
		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + id);

		}
	}
	
	
	public List<CourseDto> getCourses() {
		List<Course> courses = this.courseRepository.findAll();
		if (courses.isEmpty()) {
			throw new StudentResourceNotFoundException("Record not found ");
		} else {
			// convert entity to DTO
			List<CourseDto> studentResponse = courses.stream()
					.map(student -> modelMapper.map(student, CourseDto.class)).collect(Collectors.toList());
			return studentResponse;
		}
	}
	
	public List<CourseDto> getAllCoursesMappedByStudentId(int studentId) {
		Optional<Student> studentDb = this.studentRepository.findById(studentId);
		if (studentDb.isPresent()) {
			Student studentUpdate = studentDb.get();
			List<Course> course = studentUpdate.getCourse();
			List<CourseDto> studentResponse = course.stream()
					.map(student -> modelMapper.map(student, CourseDto.class)).collect(Collectors.toList());
			return studentResponse;

		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + studentId);

		}
	}

}
