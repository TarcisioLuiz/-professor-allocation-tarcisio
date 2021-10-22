package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Department;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class DepartmentServiceTest {

	@Autowired
    DepartmentService service;

    @Test
    public void findAll() {
        List<Department> departments = service.findAll(null);
        departments.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;
        Department department = service.findById(id);
        System.out.println(department);
    }

    @Test
    public void save() {
        Department department = new Department();
        department.setId(null);
        department.setName("Computação 1");
        department = service.create(department);
        System.out.println(department);
    }

    @Test
    public void update() {
        Department department = new Department();
        department.setId(1L);
        department.setName("Computação 2");
        department = service.update(department);
        System.out.println(department);
    }

    @Test
    public void deleteById() {
        Long id = 1L;
        service.deleteById(id);
    }

    @Test
    public void deleteAll() {
        service.deleteAll();
    }
}
