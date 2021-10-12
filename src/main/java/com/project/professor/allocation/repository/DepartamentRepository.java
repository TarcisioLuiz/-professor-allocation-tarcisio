package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Department;

@Repository
public interface DepartamentRepository extends JpaRepository<Department, Long>{

	Department create(Department department);
	
	List<Department> findAll();
	
	Optional<Department> findById(Long id);
	
	Department update(Department department);
	
	void delete(Long id);
	
	void deleteAllInBatch (Long id);
}
