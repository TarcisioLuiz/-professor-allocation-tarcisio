package com.project.professor.allocation.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import com.project.professor.allocation.entity.Allocation;

@SpringBootTest
@TestPropertySource(locations = "classpath:application.properties")
public class AllocationServiceTest {

	SimpleDateFormat sdf = new SimpleDateFormat("HH:mmZ");
	
	@Autowired
	AllocationService service;
	
	@Test
	public void findAll() {
		List<Allocation> alloc = service.findAll();
		System.out.println(alloc);
	}
	
	@Test
    public void findByProfessor() {
        Long id = 1L;
        List<Allocation> allocations = service.findByProfessor(id);
        allocations.forEach(System.out::println);
    }

    @Test
    public void findByCourse() {
        Long id = 1L;
        List<Allocation> allocations = service.findByCourse(id);
        allocations.forEach(System.out::println);
    }

    @Test
    public void findById() {
        Long id = 1L;
        Allocation allocation = service.findById(id);
        System.out.println(allocation);
    }

    @Test
    public void save() throws ParseException {
        Allocation allocation = new Allocation();
        allocation.setId(null);
        allocation.setDay(DayOfWeek.THURSDAY);
        allocation.setStart(sdf.parse("19:00-0300"));
        allocation.setEnd(sdf.parse("22:30-0300"));
        allocation.setProfessorId(1L);
        allocation.setCourseId(1L);

        allocation = service.create(allocation);
        System.out.println(allocation);
    }

    @Test
    public void update() throws ParseException {
        Allocation allocation = new Allocation();
        allocation.setId(1L);
        allocation.setDay(DayOfWeek.MONDAY);
        allocation.setStart(sdf.parse("19:00-0300"));
        allocation.setEnd(sdf.parse("20:00-0300"));
        allocation.setProfessorId(1L);
        allocation.setCourseId(1L);

        allocation = service.update(allocation);
        System.out.println(allocation);
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
