package com.venkat.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.venkat.dao.CourseRepository;
import com.venkat.dao.StudentProfileRepository;
import com.venkat.dao.StudentRepository;
import com.venkat.entities.Course;
import com.venkat.entities.Gender;
import com.venkat.entities.Student;
import com.venkat.entities.StudentProfile;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {

	@InjectMocks
	private StudentService studentService;

	@Mock
	private StudentRepository studentRepository;

	@Mock
	private StudentProfileRepository studentProfileRepository;

	@Mock
	private CourseRepository courseRepository;

	@Test
	void testCreateStudent() {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");
		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));

		Student student = new Student("Nani", "java", "abc@gmail.com", studentProfile, courselist1, 1);

		when(studentRepository.save(student)).thenReturn(student);

		Student student1 = studentService.createStudent(student);
		assertEquals(student1.getId(), student.getId());
		assertEquals("Nani", student.getName());
		assertEquals(student1.getTech(), student.getTech());
		assertEquals(student1.getEmail(), student.getEmail());
		assertEquals(student1.getStudentProfile(), student.getStudentProfile());
		assertEquals(student1.getCourse(), student.getCourse());
		assertEquals(student1.getStudentProfile().getPhoneNumber(), student.getStudentProfile().getPhoneNumber());
		assertEquals(student1.getStudentProfile().getAddress(), student.getStudentProfile().getAddress());

	}

	@Test
	void testGetAllStudent() {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");
		StudentProfile studentProfile1 = new StudentProfile();
		studentProfile1.setAddress("Mumbai");
		studentProfile1.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile1.setGender(Gender.MALE);
		studentProfile1.setPhoneNumber("1234567899");
		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));

		when(studentRepository.findAll()).thenReturn(Stream
				.of(new Student("Nani", "Java", "abc@gmail.com", studentProfile, courselist1, 1),
						new Student("Sai", "Python", "moh@gmail.com", studentProfile1, courselist1, 2))
				.collect(Collectors.toList()));

		assertEquals(2, studentService.getAllStudent().size());
	}

	@Test
	void testGetStudentById() {
		Student student = new Student();
		student.setId(1);
		student.setName("Kumar");
		student.setTech("Java");
		student.setEmail("Java@gmail.com");
		StudentProfile studentProfile1 = new StudentProfile();
		studentProfile1.setAddress("Mumbai");
		studentProfile1.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile1.setGender(Gender.MALE);
		studentProfile1.setPhoneNumber("1234567899");
		student.setStudentProfile(studentProfile1);

		student.setCourse(Arrays.asList(new Course(1, "Java", 100)));
		when(this.studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Student student1 = studentService.getStudentById(1);
		assertEquals("Kumar", student1.getName());
	}

	@Test
	void testDeleteStudent() {
		Student student = new Student();
		student.setId(1);
		when(studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		studentService.deleteStudent(1);
		verify(studentRepository).delete(student);

	}

	@Test
	void testUpdateStudent() {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");
		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));
		Student student = new Student("Nani", "Java", "abc@gmail.com", studentProfile, courselist1, 1);
		Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(student));
		studentService.updateStudent(1, new Student("Sai", "java", "abc@gmail.com", studentProfile, courselist1, 1));
		assertEquals("Sai", student.getName());
	}

	/* Testing Student profile */

	@Test
	void testUpdateStudentProfile() {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");
		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));
		new Student("Nani", "Java", "abc@gmail.com", studentProfile, courselist1, 1);
		Mockito.when(studentProfileRepository.findByStudentId(1)).thenReturn(Optional.of(studentProfile));
		studentService.updateStudentProfile(1, new StudentProfile( "1234567899", "mumbai", Gender.MALE, LocalDate.of(1997, 12, 26)));
		assertEquals("mumbai", studentProfile.getAddress());

	}

	@Test
	void testGetStudentProfileById() {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");
		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));
		Student student = new Student("Nani", "Java", "abc@gmail.com", studentProfile, courselist1, 1);
		when(this.studentProfileRepository.findById(student.getId())).thenReturn(Optional.of(studentProfile));
		StudentProfile student1 = studentService.getStudentProfileById(1);
		assertEquals("Mumbai", student1.getAddress());
	}

	@Test
	void testGetAllStudentProfile() {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");
		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));
		new Student("Nani", "Java", "abc@gmail.com", studentProfile, courselist1, 1);

		when(studentProfileRepository.findAll()).thenReturn(Stream.of(studentProfile).collect(Collectors.toList()));

		assertEquals(1, studentService.getAllStudentProfile().size());

	}

	@Test
	void testGetStudentProfileMappedByStudentId() {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");
		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));
		Student student = new Student("Nani", "Java", "abc@gmail.com", studentProfile, courselist1, 1);
		when(this.studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		StudentProfile student1 = studentService.getStudentProfileMappedByStudentId(1);
		assertEquals("Mumbai", student1.getAddress());

	}

	/* service Test Implementation for Courses */

	@Test
	void testUpdateCourse() {
		Course course = new Course(1, "java", 100);
		Mockito.when(courseRepository.findById(1)).thenReturn(Optional.of(course));
		studentService.updateCourse(1, new Course(1, "Python", 99));
		assertEquals("Python", course.getC_name());

	}

	@Test
	void testGetCourseById() {

		Course course = new Course(1, "java", 100);

		when(this.courseRepository.findById(1)).thenReturn(Optional.of(course));
		Course student1 = studentService.getCourseById(1);

		assertEquals(student1.getC_name(), course.getC_name());

	}

	@Test
	void testGetCourses() {
		when(courseRepository.findAll()).thenReturn(
				Stream.of(new Course(1, "java", 100), new Course(2, "python", 99)).collect(Collectors.toList()));
		assertEquals(courseRepository.findAll().size(), studentService.getCourses().size());

	}

	@Test
	void testGetAllCoursesMappedByStudentId() {
		Student student = new Student();
		student.setId(1);
		student.setCourse(Arrays.asList(new Course(1, "Java", 100), new Course(2, "Python", 99)));

		when(this.studentRepository.findById(student.getId())).thenReturn(Optional.of(student));
		Student student1 = studentService.getStudentById(1);
		assertEquals(student1.getCourse().size(), student.getCourse().size());

	}
	
//	@Test
//	void testUpdateCoursesMappedByStudentId() {
//		Student student = new Student();
//		student.setId(1);
//		student.setCourse(Arrays.asList(new Course(1, "Java", 100), new Course(2, "Python", 99)));
//		Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(student));
//		List<Course> courselist1 = new ArrayList<>(
//				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));
//		
//		studentService.updateCoursesMappedByStudentId(1,courselist1);
//		System.out.println(courselist1.size());
//	}

}
