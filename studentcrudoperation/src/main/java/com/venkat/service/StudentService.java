package com.venkat.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.venkat.Exception.StudentResourceNotFoundException;
import com.venkat.dao.CourseRepository;
import com.venkat.dao.StudentProfileRepository;
import com.venkat.dao.StudentRepository;
import com.venkat.entities.Course;
import com.venkat.entities.Student;
import com.venkat.entities.StudentProfile;

@Service
@Transactional
public class StudentService implements StudentServiceImpl {

	@Autowired
	private StudentRepository studentRepository;

	@Autowired
	private StudentProfileRepository studentProfileRepository;

	@Autowired
	private CourseRepository courseRepository;

	@Autowired
	private FeignInterface feignInterface;

	private final ModelMapper modelMapper = new ModelMapper();

	@Override
	public Student createStudent(Student student) {

		return studentRepository.save(student);
	}

	@Override
	public Student updateStudent(int id, Student student) {
		Optional<Student> studentDb = this.studentRepository.findById(id);
		if (studentDb.isPresent()) {
			Student studentUpdate = studentDb.get();
			student.setId(studentUpdate.getId());
			studentUpdate.setName(student.getName());
			studentUpdate.setTech(student.getTech());
			studentUpdate.setEmail(student.getEmail());
			studentUpdate.getStudentProfile().setPhoneNumber(student.getStudentProfile().getPhoneNumber());
			studentUpdate.getStudentProfile().setAddress(student.getStudentProfile().getAddress());
			studentUpdate.getStudentProfile().setGender(student.getStudentProfile().getGender());
			studentUpdate.getStudentProfile().setBirthOfDate(student.getStudentProfile().getBirthOfDate());
			studentUpdate.setCourse(student.getCourse());
			studentRepository.save(student);
			return studentUpdate;
		} else {

			throw new StudentResourceNotFoundException("Record not found with id : " + student.getId());

		}

	}

	@Override
	public List<Student> getAllStudent() {

		return this.studentRepository.findAll();

	}

	@Override
	public Student getStudentById(int id) {
		// StudentRepository studentRepository = null;
		Optional<Student> studentDb = this.studentRepository.findById(id).map(student -> {
			student.getCourse();
			return student;
		});
		if (studentDb.isPresent()) {
			return studentDb.get();
		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + id);

		}

	}

	@Override
	public void deleteStudent(int id) {
		Optional<Student> studentDb = this.studentRepository.findById(id);

		if (studentDb.isPresent()) {
			this.studentRepository.delete(studentDb.get());
		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + id);
		}

	}

	/* service Implentation for Student-profile */

	@Override
	public StudentProfile updateStudentProfile(int studentId, @Valid StudentProfile profile) {

		StudentProfile studentProfile = studentProfileRepository.findByStudentId(studentId).map(obj -> {
			obj.setAddress(profile.getAddress());
			obj.setGender(profile.getGender());
			obj.setBirthOfDate(profile.getBirthOfDate());
			obj.setPhoneNumber(profile.getPhoneNumber());
			return obj;
		}).orElseThrow(() -> new StudentResourceNotFoundException("record not found with id: " + studentId));
		return studentProfileRepository.save(studentProfile);

	}

	@Override
	public StudentProfile getStudentProfileById(int id) {
		Optional<StudentProfile> studentDb = this.studentProfileRepository.findById(id);
		if (studentDb.isPresent()) {
			return studentDb.get();
		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + id);

		}
	}

	@Override
	public List<StudentProfile> getAllStudentProfile() {

		return this.studentProfileRepository.findAll();
	}

	@Override
	public StudentProfile getStudentProfileMappedByStudentId(int studentId) {
		Optional<Student> studentDb = this.studentRepository.findById(studentId);
		if (studentDb.isPresent()) {
			Student studentUpdate = studentDb.get();
			return studentUpdate.getStudentProfile();

		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + studentId);

		}
	}

//	Service implementation for courses

	@Override
	public Course updateCourse(int id, Course course) {

		Course cdb = courseRepository.findById(id).map(obj -> {
			obj.setC_name(course.getC_name());
			obj.setMarks(course.getMarks());
			return obj;
		}).orElseThrow(() -> new StudentResourceNotFoundException("Course id not found with id: " + id));
		return courseRepository.save(cdb);
	}

	@Override
	public Course getCourseById(int id) {
		Optional<Course> courseDb = this.courseRepository.findById(id);
		if (courseDb.isPresent()) {
			return courseDb.get();
		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + id);

		}
	}

	@Override
	public List<Course> getCourses() {
		return this.courseRepository.findAll();
	}

	@Override
	public List<Course> getAllCoursesMappedByStudentId(int studentId) {
		Optional<Student> studentDb = this.studentRepository.findById(studentId);
		if (studentDb.isPresent()) {
			Student studentUpdate = studentDb.get();
			return studentUpdate.getCourse();

		} else {
			throw new StudentResourceNotFoundException("Record not found with id : " + studentId);

		}
	}

	@Override
	public List<Course> updateCoursesMappedByStudentId(int id, List<Course> courses) {
		Optional<Student> student = studentRepository.findById(id);
		if (student.isPresent()) {
			Student newStudent = student.get();
			newStudent.setCourse(courses);
//			Student save = studentRepository.save(newStudent);
			return studentRepository.save(newStudent).getCourse();
		}
		throw new StudentResourceNotFoundException("Record not found with id : " + id);
	}

//	public Course getAllCoursesMappedByStudentIdBy(int id, int courseId) {
//		Optional<Student> studentDb = this.studentRepository.findById(id);
//		if (studentDb.isPresent()) {
//			Student studentUpdate = studentDb.get();
//			List<Course> courses = studentUpdate.getCourse();
//
//			return courses.stream().filter(course -> course.getId() = !null).findFirst()courseId
//					.orElseThrow(() -> new StudentResourceNotFoundException("Record not found with id : "));
//
//	    	
//			return courses.get(courseId-1);
//		}
//
//	}

	public Student create(Student student) {
		String value = feignInterface.getDetails();
		student.setFeign(value);
		return studentRepository.save(student);

	}

}
