package com.venkat.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "Courses")
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@NotNull
	@Size(min = 2, message = "Enter valid Subject name...!")
	private String c_name;

	@NotNull(message = "fill the marks field")
	@Min(value = 0, message = "Marks range in 0 to 100..!")
	@Max(value = 100, message = "Marks range in 0 to 100..!")
	private int marks;

	public Course(Integer id, @NotNull @Size(min = 2, message = "Enter valid Subject name...!") String c_name,
			@NotNull(message = "fill the marks field") @Min(value = 0, message = "Marks range in 0 to 100..!") @Max(value = 100, message = "Marks range in 0 to 100..!") int marks) {
		super();
		this.id = id;
		this.c_name = c_name;
		this.marks = marks;
	}

	public Course() {
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
