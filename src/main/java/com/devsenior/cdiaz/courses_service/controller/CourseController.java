package com.devsenior.cdiaz.courses_service.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.devsenior.cdiaz.courses_service.model.Course;
import com.devsenior.cdiaz.courses_service.service.CourseService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

// DRY - Don't Repeat Yourself
@Tag(name = "Cursos", description = "API para la gestión de cursos académicos")
@RestController
@RequestMapping("/api/cursos")
public class CourseController {

    private CourseService courseService;

    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @Operation(summary = "Listar todos los cursos", description = "Retorna la información de los cursos habilitados en el sistema.")
    @ApiResponse(responseCode = "200", description = "Listado de cursos encontrados de manera exitosa")
    @ApiResponse(responseCode = "205", description = "No hay cursos disponibles actualmente", content = @Content())
    @ApiResponse(responseCode = "404", description = "No hay cursos", content = @Content())
    @GetMapping
    public List<Course> getAllCourses() {
        return courseService.getAll();
    }

    @Operation(summary = "Buscar un curso por Id", description = "Consulta la informacion del curso con id dado")

    @ApiResponse(responseCode = "200", description = "Curso encontrado exitosamente")
    @ApiResponse(responseCode = "500", description = "El curso no fue encontrado", content = @Content())
    @GetMapping(value = "/{id}", produces = { MediaType.APPLICATION_JSON_VALUE })
    public Course getCourseById(@PathVariable Long id) {
        return courseService.getOneById(id);
    }

    @GetMapping("/buscar")
    public List<Course> getCourseContainsText(@RequestParam(value = "nombre") String name) {
        return courseService.getAllThatContainsName(name);
    }

    @Operation(summary = "Crear un nuevo curso")
    @PostMapping
    public Course createCourse(
            @RequestBody(description = "Datos del nuevo curso a crear", required = true,
                    content = @Content(schema = @Schema(implementation = Course.class)))
            @org.springframework.web.bind.annotation.RequestBody
            @Valid Course course) {
        return courseService.create(course);
    }

    @PutMapping("/{id}")
    public Course updateCourse(@PathVariable 
            Long id,
            @RequestBody(description = "Datos del nuevo curso a crear", required = true,
            content = @Content(schema = @Schema(implementation = Course.class)))
            @org.springframework.web.bind.annotation.RequestBody
            @Valid Course course) {
        return courseService.update(id, course);
    }

    @DeleteMapping("/{id}")
    public void deleteCourse(@PathVariable Long id) {
        courseService.delete(id);
    }

}
