package com.project.professor.allocation.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.professor.allocation.entity.Allocation;

@Repository
public interface AllocationRepository extends JpaRepository<Allocation, Long>{

	List<Allocation> findByCourseId (Long courseId);
	
	List<Allocation> findByProfessorId (Long professorId);

	Allocation create(Allocation allocation);
	
	List<Allocation> findAll();
	
	Optional<Allocation> findById(Long id);
	
	Allocation update(Allocation allocation);
	
	void delete(Long id);
	
	void deleteAllInBatch (Long id);

}
