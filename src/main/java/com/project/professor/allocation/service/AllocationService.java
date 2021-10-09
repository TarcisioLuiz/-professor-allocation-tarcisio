package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {

	private final AllocationRepository repository;
	private final ProfessorService professorService;
	private final CourseService courseService;

	public AllocationService(AllocationRepository repository, ProfessorService professorService,
			CourseService courseService) {
		super();
		this.repository = repository;
		this.professorService = professorService;
		this.courseService = courseService;
	}

	public List<Allocation> findAll() {
		List<Allocation> allocations = repository.findAll();
		return allocations;
	}

	public Allocation findById(Long id) {
		Allocation allocation = repository.findById(id).orElse(null);
		return allocation;
	}

	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		Allocation alloc = save(allocation);
		return alloc;
	}

	public Allocation update(Allocation allocation) {
		if (repository.existsById(allocation.getId())) {
			Allocation alloc = save(allocation);
			return alloc;
		}
		return null;
	}

	private Allocation save(Allocation allocation) {
		Allocation allocSave = repository.save(allocation);
		Professor professor = professorService.findById(allocation.getProfessorId());
		Course course = courseService.findById(allocation.getCourseId());
		allocSave.setProfessor(professor);
		allocSave.setCourse(course);
		return allocSave;
	}
	
	public void delete(Long allocationId) {
		if (repository.existsById(allocationId)) {
			repository.deleteById(allocationId);
		}
	}
	
	public void deleteAll() {
		repository.deleteAllInBatch();
	}
}
