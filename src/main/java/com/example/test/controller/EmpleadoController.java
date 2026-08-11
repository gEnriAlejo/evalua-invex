package com.example.test.controller;

import com.example.test.dtos.EmpleadoDto;
import com.example.test.records.EmpleadoR;
import com.example.test.service.IEmpleadoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Clase controller
 * @author Henry
 */
@Slf4j
@RestController
@RequestMapping(path = "/employee")
public class EmpleadoController {

    private final IEmpleadoService empleadoService;

    public EmpleadoController(IEmpleadoService empleadoService) {
        this.empleadoService = empleadoService;
    }

    /**
     * Obtiene lista de empleados
     *
     * @return lista de empleados
     */
    @GetMapping
    private ResponseEntity<List<EmpleadoDto>> getAllEmployees() {
        return ResponseEntity.ok(empleadoService.getAllEmployees());
    }

    /**
     * Inserta lista de empleados
     *
     * @param empleados
     * @return lista de empleados creados
     */
    @PostMapping
    private ResponseEntity<List<EmpleadoDto>> createEmployees(
                @RequestBody
                @NotNull(message = "La lista de empleados no puede estar vacía")
                @NotEmpty(message = "La lista de empleados no puede estar vacía")
                List<@Valid EmpleadoR> empleados) {
        log.info("Registros a crear: {}", empleados.size());

        List<EmpleadoDto> empleadosDto = empleadoService.createEmployees(empleados);
        return new ResponseEntity<>(empleadosDto, HttpStatus.CREATED);
    }

    /**
     * Actualiza empleado
     *
     * @param id
     * @param empleado
     * @return empleado actualizado
     */
    @PutMapping("/{id}")
    private ResponseEntity<EmpleadoDto> updateEmployee(@PathVariable Integer id, @RequestBody EmpleadoR empleado) {
        log.info("id de registro a modificar: {}", id);

        EmpleadoDto empleadoDto = empleadoService.updateEmployee(id, empleado);
        return ResponseEntity.ok(empleadoDto);
    }

    /**
     * Elimina empleado de BD
     *
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    private ResponseEntity<Void> updateEmployee(@PathVariable Integer id) {
        log.info("id de registro a eliminar: {}", id);

        empleadoService.deleteEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
