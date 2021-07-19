package com.venkat.entities;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "students")
//@Setter
//@Getter
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class Student {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min = 2, message = "Name should have atleast 2 characters...!")
	private String name;

	@NotNull
	@Size(min = 2, message = "Technology should have atleast 2 characters...!")
	private String tech;

	@NotNull
	@Email(message = "Enter valid email...!")
	private String email;

	private String feign;

	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "Stud_Profile_id")
	private StudentProfile studentProfile;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "student_id")
	private List<Course> course = new ArrayList<>();

	public String getFeign() {
		return feign;
	}

	public void setFeign(String feign) {
		this.feign = feign;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTech() {
		return tech;
	}

	public void setTech(String tech) {
		this.tech = tech;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public StudentProfile getStudentProfile() {
		return studentProfile;
	}

	public void setStudentProfile(StudentProfile studentProfile) {
		this.studentProfile = studentProfile;
	}

	public List<Course> getCourse() {
		return course;
	}

	public void setCourse(List<Course> course) {
		this.course = course;
	}

	public Student(@NotNull @Size(min = 2, message = "Name should have atleast 2 characters...!") String name,
			@NotNull @Size(min = 2, message = "Technology should have atleast 2 characters...!") String tech,
			@NotNull @Email(message = "Enter valid email...!") String email, StudentProfile studentProfile,
			List<Course> course, Integer id) {
		super();
		this.id = id;
		this.name = name;
		this.tech = tech;
		this.email = email;
		this.studentProfile = studentProfile;
		this.course = course;

	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", tech=" + tech + ", email=" + email + "]";
	}

}
