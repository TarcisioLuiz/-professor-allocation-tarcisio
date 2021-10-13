package com.project.professor.allocation.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.project.professor.allocation.entity.Allocation;
import com.project.professor.allocation.entity.Course;
import com.project.professor.allocation.entity.Professor;
import com.project.professor.allocation.repository.AllocationRepository;

@Service
public class AllocationService {

	private final AllocationRepository repository;
	private final ProfessorService professorService;
	private final CourseService courseService;

	public AllocationService(AllocationRepository repository, ProfessorService professorService,
			CourseService courseService) {
		super();
		this.repository = repository;
		this.professorService = professorService;
		this.courseService = courseService;
	}

	public List<Allocation> findAll() {
		List<Allocation> allocations = repository.findAll();
		return allocations;
	}

	public Allocation findById(Long id) {
		Allocation allocation = repository.findById(id).orElse(null);
		return allocation;
	}
	
	public List<Allocation> findByProfessor(Long professorId) {
        return repository.findByProfessorId(professorId);
    }

    public List<Allocation> findByCourse(Long courseId) {
        return repository.findByCourseId(courseId);
    }

	public Allocation create(Allocation allocation) {
		allocation.setId(null);
		Allocation alloc = save(allocation);
		return alloc;
	}

	public Allocation update(Allocation allocation) {
		if (repository.existsById(allocation.getId())) {
			Allocation alloc = save(allocation);
			return alloc;
		}
		return null;
	}
	
	public void delete(Long allocationId) {
		if (repository.existsById(allocationId)) {
			repository.deleteById(allocationId);
		}
	}
	
	public void deleteAll() {
		repository.deleteAllInBatch();
	}
	//a "!" serve para negar o resultado retornado assim se vinher true ele dira que é false e vice versa
	public Allocation save(Allocation allocation) {
		if (!isEndHourGreaterThanStartHour(allocation) || hasCollision(allocation)) {
            throw new RuntimeException();
        } else {
        	Allocation allocSave = repository.save(allocation);
		Professor professor = professorService.findById(allocation.getProfessorId());
		Course course = courseService.findById(allocation.getCourseId());
		allocSave.setProfessor(professor);
		allocSave.setCourse(course);
		return allocSave;
        }
	}
	
	// vai olhar se a hora final é maior que a hora inicial
	boolean isEndHourGreaterThanStartHour(Allocation allocation) {
        return allocation != null && allocation.getStart() != null && allocation.getEnd() != null
                && allocation.getEnd().compareTo(allocation.getStart()) > 0;
    }//se tudo tiver ok ele vai retornar true
	
	boolean hasCollision(Allocation newAllocation) {
        boolean hasCollision = false;

        //pega todas as alocações do professor
        List<Allocation> currentAllocations = repository.findByProfessorId(newAllocation.getProfessorId());

        //para cada alocação cadastrada ele testa para vÊ se choca com a nova
        for (Allocation currentAllocation : currentAllocations) {
            hasCollision = hasCollision(currentAllocation, newAllocation);
            if (hasCollision) {
                break;
            }//se hasCollision = true ele vai parar o for e retornar true
        }

        return hasCollision; //se tudo tiver ok na comparação do hasCollision ele irá enviar false
    }
	
	// teste de comparação da alocação atual com a alocação nova
	private boolean hasCollision(Allocation currentAllocation, Allocation newAllocation) {
        return !currentAllocation.getId().equals(newAllocation.getId())
                && currentAllocation.getDay() == newAllocation.getDay()
                && currentAllocation.getStart().compareTo(newAllocation.getEnd()) < 0
                && newAllocation.getStart().compareTo(currentAllocation.getEnd()) < 0;
    }//se tudo estiver ok irá retornar falso por conta da "!" que ira negar o true do teste
}
