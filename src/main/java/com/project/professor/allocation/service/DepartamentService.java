package com.project.professor.allocation.service;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.repository.DepartamentRepository;

@Service
public class DepartamentService {

	private final DepartamentRepository repository;

	public DepartamentService(DepartamentRepository repository) {
		super();
		this.repository = repository;
	}
	
	
}
