package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{

	List<Professor> findByNameContainingIgnoreCase(String name);
	
	List<Professor> findByDepartmentId(Long department);
	
	Professor create(Professor professor);
	
	List<Professor> findAll();
	
	Optional<Professor> findById(Long id);
	
	Professor update(Professor professor);
	
	void delete(Long id);
	
	void deleteAllInBatch (Long id);
	
}
