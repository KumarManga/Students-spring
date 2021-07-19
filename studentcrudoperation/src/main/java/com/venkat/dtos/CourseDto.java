package com.venkat.dtos;

public class CourseDto {
	
	private Integer id;

	
	private String c_name;

	
	private int marks;

	public CourseDto(Integer id, String c_name, int marks) {
		super();
		this.id = id;
		this.c_name = c_name;
		this.marks = marks;
	}

	public CourseDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getC_name() {
		return c_name;
	}

	public void setC_name(String c_name) {
		this.c_name = c_name;
	}

	public int getMarks() {
		return marks;
	}

	public void setMarks(int marks) {
		this.marks = marks;
	}

	@Override
	public String toString() {
		return "Course [id=" + id + ", c_name=" + c_name + ", marks=" + marks + "]";
	}

}
