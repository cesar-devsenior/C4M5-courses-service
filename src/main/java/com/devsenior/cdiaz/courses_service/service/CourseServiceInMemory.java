package com.devsenior.cdiaz.courses_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.devsenior.cdiaz.courses_service.exception.CourseNotFoundException;
import com.devsenior.cdiaz.courses_service.model.Course;

// DRY
@Service
public class CourseServiceInMemory implements CourseService {

    private AtomicLong consecutive;
    private List<Course> courses;

    public CourseServiceInMemory() {
        courses = new ArrayList<>();
        consecutive = new AtomicLong(1);
    }

    @Override
    public List<Course> getAll() {
        // return new ArrayList<Course>(courses);
        return courses.stream().toList();
    }

    @Override
    public Course getOneById(Long id) {
        validateId(id);

        return courses.stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new CourseNotFoundException(
                        String.format("El curso con Id '%d' no existe!", id)));
    }

    @Override
    public List<Course> getAllThatContainsName(String partialName) {
        if (isBlank(partialName)) {
            return getAll();
        }

        return courses.stream()
                .filter(c -> c.getName().toLowerCase().contains(partialName.toLowerCase())
                        || c.getDescription().toUpperCase().contains(partialName.toUpperCase()))
                .toList();
    }

    @Override
    public Course create(Course course) {
        validateCourse(course);

        // Generamos el ID
        course.setId(consecutive.getAndIncrement());

        // agregar el curso a la lista
        courses.add(course);

        return course;
    }

    @Override
    public Course update(Long id, Course course) {
        validateId(id);
        validateCourse(course);

        var existingCourse = getOneById(id);
        existingCourse.setName(course.getName());
        existingCourse.setCode(course.getCode());
        existingCourse.setDescription(course.getDescription());
        existingCourse.setInitialDate(course.getInitialDate());
        existingCourse.setFinalDate(course.getFinalDate());
        existingCourse.setCredits(course.getCredits());

        return existingCourse;
    }

    @Override
    public void delete(Long id) {
        validateId(id);

        var existingCourse = getOneById(id);

        courses.remove(existingCourse);
    }

    private boolean isBlank(String data) {
        return data == null || data.isBlank();
    }

    private void validateId(Long id) {
        // if (id == null) {
        //     throw new IllegalArgumentException("El Id está vacio");
        // }
        // if (id < 0) {
        //     throw new IllegalArgumentException("El Id no puede ser negativo");
        // }
    }

    private void validateCourse(Course course){ 
        // if (course == null) {
        //     throw new IllegalArgumentException("No fue enviado un curso a guardar.");
        // }
        // if (isBlank(course.getName())) {
        //     throw new IllegalArgumentException("El nombre del curso es obligatorio.");
        // }
        // if (course.getCredits() <= 0) {
        //     throw new IllegalArgumentException("Los créditos del curso deben ser mayores a 0.");
        // }
        if (course.getInitialDate().isAfter(course.getFinalDate())) {
            throw new IllegalArgumentException("La fecha inicio debe ser anterior a la fecha final.");
        }
        // ...
    }

}
