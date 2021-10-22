package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.repository.CourseRepository;

@Service
public class CourseService {

	private final CourseRepository repository;

	public CourseService(CourseRepository repository) {
		super();
		this.repository = repository;
	}
	
	public List<Course> findAll() {
		List<Course> courses = repository.findAll();
		return courses;
	}

	public Course findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Course create(Course course) {
		course.setId(null);
		return save(course);
	}

	public Course update(Course course) {
		if (repository.existsById(course.getId())) {
			return save(course);
			
		}
		return null;
	}

	private Course save(Course course) {
		Course crsSave = repository.save(course);
		return crsSave;
	}
	
	public void deleteById(Long courseId) {
		if (repository.existsById(courseId)) {
			repository.deleteById(courseId);
		}
	}
	
	public void deleteAll() {
		repository.deleteAllInBatch();
	}
}
