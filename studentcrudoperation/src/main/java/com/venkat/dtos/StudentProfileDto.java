package com.venkat.dtos;

import java.time.LocalDate;

import com.venkat.entities.Gender;

public class StudentProfileDto {

	private Integer id;

	private String phoneNumber;

	private String address;

	private Gender gender;

	private LocalDate birthOfDate;

	private StudentDto studentDto;

	public StudentProfileDto(String phoneNumber, String address, Gender gender, LocalDate birthOfDate) {
		super();
		this.phoneNumber = phoneNumber;
		this.address = address;
		this.gender = gender;
		this.birthOfDate = birthOfDate;
	}

	public StudentProfileDto() {
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

	@Override
	public String toString() {
		return "StudentProfile [id=" + id + ", phoneNumber=" + phoneNumber + ", address=" + address + ", gender="
				+ gender + ", birthOfDate=" + birthOfDate + "]";
	}

}
