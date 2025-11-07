package com.devsenior.cdiaz.courses_service.service;

import java.util.List;

import com.devsenior.cdiaz.courses_service.exception.CourseNotFoundException;
import com.devsenior.cdiaz.courses_service.model.Course;

// JAVADOC
/**
 * Clase de logica de negocio para gestiionar cursos.
 */
public interface CourseService {

    /**
     * Lista todos los cursos existentes en el sistema.
     *
     * @return Todos los cursos existentes. En caso que no haya ningun curso, devuelve una lista vacía.
     */
    List<Course> getAll();

    /**
     * Consulta el curso que tenga asignado el id dado.
     *
     * @param id El identificador del curso a buscar.
     * @return La informacion del curso que tiene el id dado.
     * @throws IllegalArgumentException Si el id es nulo o menor a 0.
     * @throws CourseNotFoundException Si no se encuentra el id en los cursos del sistema.
     */
    Course getOneById(Long id);

    List<Course> getAllThatContainsName(String partialName);

    Course create(Course course);

    Course update(Long id, Course course);

    void delete(Long id);
}
