package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Department;
import com.project.professor.allocation.repository.DepartamentRepository;

@Service
public class DepartmentService {

	private final DepartamentRepository repository;

	public DepartmentService(DepartamentRepository repository) {
		super();
		this.repository = repository;
	}
	
	public List<Department> findAll(String name) {
		if (name == null) {
            return repository.findAll();
        } else {
            return repository.findByNameContainingIgnoreCase(name);
        }
	}

	public Department findById(Long id) {
		return repository.findById(id).orElse(null);
	}

	public Department create(Department department) {
		department.setId(null);
		return save(department);
	}

	public Department update(Department department) {
		if (repository.existsById(department.getId())) {
			return save(department);
		}
		return null;
	}

	private Department save(Department department) {
		return repository.save(department);
	}
	
	public void deleteById(Long departmentId) {
		if (repository.existsById(departmentId)) {
			repository.deleteById(departmentId);
		}
	}
	
	public void deleteAll() {
		repository.deleteAllInBatch();
	}
}
