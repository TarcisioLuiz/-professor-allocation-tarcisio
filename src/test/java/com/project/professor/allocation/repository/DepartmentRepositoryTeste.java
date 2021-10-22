package com.project.professor.allocation.repository;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@DataJpaTest
@AutoConfigureTestDatabase (replace = Replace.NONE)
@Rollback(false)
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentRepositoryTeste {

	@Autowired
	DepartamentRepository repository;
	
	@Test
	public void findById() {
		Department search = repository.findById(1L).orElse(null);
		System.out.println(search);
	}
	
	@Test
	public void create() {
		Department mamute = new Department(null, "mamute");
		Department mamute2 = repository.save(mamute);
		System.out.println(mamute2);
	}
	
	@Test
	public void update() {
		Department mamute = new Department(5L, "mamute2");
		Department mamute2 = repository.save(mamute);
		System.out.println(mamute2);
	}
	
	@Test
	public void delete() {
		if (repository.existsById(5L)) {
			repository.deleteById(5L);
		}
	}
}
