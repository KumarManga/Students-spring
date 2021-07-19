package com.venkat.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.venkat.entities.Course;
import com.venkat.entities.Gender;
import com.venkat.entities.Student;
import com.venkat.entities.StudentProfile;
import com.venkat.service.StudentService;

@RunWith(SpringRunner.class)
@WebMvcTest(value = StudentController.class)
class StudentControllerTest {

	@Autowired
	private MockMvc mockMvc;

	private Student student;

	@MockBean
	private StudentService studentService;

	@BeforeEach
	public void runBeforeEachTestMethodToGenerateData() {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setId(1);
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(null);
		// studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");
		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));

		student = new Student("Nani", "java", "abc@gmail.com", studentProfile, courselist1, 1);
	}
	

	@Test
	void testGetAllStudent() throws Exception {
		List<Student> listStudents = new ArrayList<>();

		listStudents.add(student);
		// listStudents.add(student2);

		Mockito.when(studentService.getAllStudent()).thenReturn(listStudents);

		String URI = "/students";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(listStudents);
		String outputInJson = result.getResponse().getContentAsString();
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);

	}

	@Test
	void testGetStudentById() throws Exception {

		

		String inputInJson = this.mapToJson(this.student);

		String URI = "/students/1";

		Mockito.when(studentService.getStudentById(1)).thenReturn(this.student);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	void testCreateStudent() throws Exception {

		Student student = new Student();

		student.setId(12);
		student.setName("Ashish");
		student.setTech("Cse");
		student.setEmail("abc@gmail.com");

		String inputInJson = this.mapToJson(this.student);

		String URI = "/students";

		Mockito.when(studentService.createStudent(Mockito.any(Student.class))).thenReturn(this.student);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.post(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	void testUpdateStudent() throws Exception {
		Student student = new Student();

		student.setId(1);
		student.setName("Ashish");
		student.setTech("Cse");
		student.setEmail("abc@gmail.com");

		String inputInJson = this.mapToJson(student);

		String URI = "/students/1";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	void testDeleteStudent() throws Exception {
		Student student = new Student();

		student.setId(1);
		student.setName("Ashish");
		student.setTech("Cse");
		student.setEmail("abc@gmail.com");

		this.mapToJson(this.student);

		String URI = "/students/1";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.delete(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	/* Controller test for student profile */

	@Test
	void testGetAllStudentProfile() throws Exception {
		List<StudentProfile> listStudents = new ArrayList<>();
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setId(1);
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(null);
		// studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");

		listStudents.add(studentProfile);

		Mockito.when(studentService.getAllStudentProfile()).thenReturn(listStudents);

		String URI = "/student-profiles";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(listStudents);
		String outputInJson = result.getResponse().getContentAsString();
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);

	}

	@Test
	void testGetStudentProfileById() throws Exception {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setId(1);
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(null);
		// studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");
		String inputInJson = this.mapToJson(studentProfile);

		String URI = "/student-profiles/1";

		Mockito.when(studentService.getStudentProfileById(1)).thenReturn(studentProfile);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	void testUpdateStudentProfile() throws Exception {
		// Student student = new Student();
		//
		// student.setId(1);
		// student.setName("Ashish");
		// student.setTech("Cse");
		// student.setEmail("abc@gmail.com");
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setId(1);
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(null);
		// studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");

		String inputInJson = this.mapToJson(studentProfile);

		String URI = "/students/1/student-profiles";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	void testGetStudentProfileMappedByStudentId() throws Exception {
		StudentProfile studentProfile = new StudentProfile();
		studentProfile.setId(1);
		studentProfile.setAddress("Mumbai");
		studentProfile.setBirthOfDate(null);
		// studentProfile.setBirthOfDate(LocalDate.of(1997, 12, 26));
		studentProfile.setGender(Gender.MALE);
		studentProfile.setPhoneNumber("1234567899");
		String inputInJson = this.mapToJson(studentProfile);

		String URI = "/students/1/student-profiles";

		Mockito.when(studentService.getStudentProfileMappedByStudentId(1)).thenReturn(studentProfile);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	/* Controller for courses one to many mapping */

	@Test
	void testUpdateCourse() throws Exception {
		Course course = new Course(1, "Java", 100);
		String inputInJson = this.mapToJson(course);

		String URI = "/courses/1";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}

	@Test
	void testGetCourseById() throws Exception {
		Course course = new Course(1, "Java", 100);

		String inputInJson = this.mapToJson(course);

		String URI = "/courses/1";

		Mockito.when(studentService.getCourseById(1)).thenReturn(course);

		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		String outputInJson = response.getContentAsString();

		Assertions.assertThat(outputInJson).isEqualTo(inputInJson);
		assertEquals(HttpStatus.OK.value(), response.getStatus());

	}

	@Test
	void testGetCourses() throws Exception {
		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));

		Mockito.when(studentService.getCourses()).thenReturn(courselist1);

		String URI = "/courses";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(courselist1);
		String outputInJson = result.getResponse().getContentAsString();
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);

	}

	@Test
	void testGetAllCoursesMappedByStudentId() throws Exception {
		Student student = new Student();
		student.setId(1);

		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));

		Mockito.when(studentService.getAllCoursesMappedByStudentId(student.getId())).thenReturn(courselist1);

		String URI = "/students/1/courses";
		RequestBuilder requestBuilder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		String expectedJson = this.mapToJson(courselist1);
		String outputInJson = result.getResponse().getContentAsString();
		Assertions.assertThat(outputInJson).isEqualTo(expectedJson);

	}
	
	
	@Test
	void testUpdateCoursesMappedByStudentId() throws Exception {
		Student student = new Student();
		student.setId(1);

		List<Course> courselist1 = new ArrayList<>(
				Arrays.asList(new Course(1, "Java", 100), (new Course(2, "Java", 100))));
		String inputInJson = this.mapToJson(courselist1);

		String URI = "/students/1/courses";

		RequestBuilder requestBuilder = MockMvcRequestBuilders.put(URI).accept(MediaType.APPLICATION_JSON)
				.content(inputInJson).contentType(MediaType.APPLICATION_JSON);

		MvcResult result = mockMvc.perform(requestBuilder).andReturn();

		MockHttpServletResponse response = result.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
		
		
	}

	private String mapToJson(Object object) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(object);
	}

}
