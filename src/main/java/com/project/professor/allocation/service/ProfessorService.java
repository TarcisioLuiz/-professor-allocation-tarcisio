package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.ProfessorRepository;

@Service
public class ProfessorService {

	private final ProfessorRepository repository;
	private final DepartmentService departmentService;
	
	public ProfessorService(ProfessorRepository repository, DepartmentService departmentService) {
		super();
		this.repository = repository;
		this.departmentService = departmentService;
	}

	public List<Professor> findAll() {
		return repository.findAll();
	}

	public Professor findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Professor create(Professor professor) {
		professor.setId(null);
		return save(professor);
	}

	public Professor update(Professor professor) {
		if (repository.existsById(professor.getId())) {
			return  save(professor);
		}
		return null;
	}

	private Professor save(Professor professor) {
		Professor profSave = repository.save(professor);
		Department department = departmentService.findById(professor.getDepartmentId());
		profSave.setDepartment(department);
		return profSave;
	}
	
	public void deleteById(Long professorId) {
		if (repository.existsById(professorId)) {
			repository.deleteById(professorId);
		}
	}
	
	public void deleteAll() {
		repository.deleteAllInBatch();
	}
}
