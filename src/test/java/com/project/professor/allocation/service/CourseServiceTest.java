package com.project.professor.allocation.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Course;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class CourseServiceTest {

	@Autowired
    CourseService service;

    @Test
    public void findAll() {
        List<Course> courses = service.findAll();
        courses.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;
        Course course = service.findById(id);
        System.out.println(course);
    }

    @Test
    public void save() {
        Course course = new Course();
        course.setId(null);
        course.setName("Java");
        course = service.save(course);
        System.out.println(course);
    }

    @Test
    public void update() {
        Course course = new Course();
        course.setId(1L);
        course.setName("Python");
        course = service.update(course);
        System.out.println(course);
    }

    @Test
    public void delete() {
        Long id = 1L;
        service.delete(id);
    }

    @Test
    public void deleteAll() {
        service.deleteAll();
    }
}
