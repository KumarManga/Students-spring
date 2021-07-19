package com.venkat.entities;

import java.time.LocalDate;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.venkat.customValidation.Phone;


@Entity
@Table(name = "Student_profiles")
public class StudentProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Phone
	private String phoneNumber;

	@NotNull
	@Size(min = 5, message = "Enter valid Address")
	private String address;

	@Enumerated(EnumType.STRING)
	private Gender gender;

	@Column(name = "Date_of_birth")
	private LocalDate birthOfDate;
	
	 
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "studentProfile")
	private Student student;

	public StudentProfile(String phoneNumber, String address, Gender gender, LocalDate birthOfDate) {
		super();
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.gender = gender;
		this.birthOfDate = birthOfDate;
	}

	public StudentProfile() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public LocalDate getBirthOfDate() {
		return birthOfDate;
	}
	public void setBirthOfDate(LocalDate birthOfDate) {
		this.birthOfDate = birthOfDate;
	}

//	public Student getStudent() {
//		return student;
//	}
//
//	public void setStudent(Student student) {
//		this.student = student;
//	}

	@Override
	public String toString() {
		return "StudentProfile [id=" + id + ", phoneNumber=" + phoneNumber + ", address=" + address + ", gender="
				+ gender + ", birthOfDate=" + birthOfDate + "]";
	}







}
