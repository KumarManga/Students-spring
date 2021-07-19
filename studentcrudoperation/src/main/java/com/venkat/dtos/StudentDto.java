package com.venkat.dtos;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class StudentDto {

	private Integer id;
	private String name;
	private String email;
	private String tech;

	private StudentProfileDto studentProfile = new StudentProfileDto();

	private List<CourseDto> course = new ArrayList<>();

//	public StudentDto(Integer id, String name, String email, String tech, List<CourseDto> course,
//			StudentProfileDto studentProfile) {
//		super();
//		this.id = id;
//		this.name = name;
//		this.email = email;
//		this.tech = tech;
//		this.course = course;
//		this.studentProfile = studentProfile;
//	}
//
//	public StudentProfileDto getStudentProfile() {
//		return studentProfile;
//	}
//
//	public void setStudentProfile(StudentProfileDto studentProfile) {
//		this.studentProfile = studentProfile;
//	}
//
//	public List<CourseDto> getCourse() {
//		return course;
//	}
//
//	public void setCourse(List<CourseDto> course) {
//		this.course = course;
//	}
//
//	public StudentDto() {
//		super();
//		// TODO Auto-generated constructor stub
//	}
//
//	@Override
//	public String toString() {
//		return "Student [id=" + id + ", name=" + name + ", tech=" + tech + ", email=" + email + "]";
//	}
//
//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getTech() {
//		return tech;
//	}
//
//	public void setTech(String tech) {
//		this.tech = tech;
//	}

}
