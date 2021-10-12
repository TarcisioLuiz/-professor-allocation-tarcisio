package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Course;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long>{

	Course create(Course course);
	
	List<Course> findAll();
	
	Optional<Course> findById(Long id);
	
	Course update(Course course);
	
	void delete(Long id);
	
	void deleteAllInBatch (Long id);
}
