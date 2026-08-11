package com.example.test.service;

import com.example.test.dtos.EmpleadoDto;
import com.example.test.records.EmpleadoR;

import java.util.List;

public interface IEmpleadoService {

    List<EmpleadoDto> getAllEmployees();
    List<EmpleadoDto> createEmployees(List<EmpleadoR> empleados);
    EmpleadoDto updateEmployee(Integer id, EmpleadoR empleado);
    void deleteEmployee(Integer id);
}
