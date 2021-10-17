package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Professor;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class ProfessorServiceTest {

	@Autowired
    ProfessorService service;

    @Test
    public void findAll() {
        List<Professor> professors = service.findAll();
        professors.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;
        Professor professor = service.findById(id);
        System.out.println(professor);
    }

    @Test
    public void save() {
        Professor professor = new Professor();
        professor.setId(null);
        professor.setName("Tiago");
        professor.setCpf("111.111.111-11");
        professor.setDepartmentId(1L);

        professor = service.create(professor);
        System.out.println(professor);
    }

    @Test
    public void update() {
        Professor professor = new Professor();
        professor.setId(1L);
        professor.setName("Amirton");
        professor.setCpf("222.222.222-22");
        professor.setDepartmentId(1L);

        professor = service.update(professor);
        System.out.println(professor);
    }

    @Test
    public void delete() {
        Long id = 1L;
        service.delete(id);
        Professor professor = service.findById(id);
        System.out.println(professor);
    }

    @Test
    public void deleteAll() {
        service.deleteAll();
        List<Professor> professors = service.findAll();
        professors.forEach(System.out::println);
    }
}
