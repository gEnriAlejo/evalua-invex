package com.example.test.service;

import com.example.test.common.exception.EmpleadoException;
import com.example.test.dtos.EmpleadoDto;
import com.example.test.records.EmpleadoR;
import com.example.test.repository.EmpleadoRepository;
import com.example.test.repository.model.Empleado;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase service
 * @author Henry
 */
@Service
public class EmpleadoService implements IEmpleadoService {

    private final EmpleadoRepository empleadoRepository;

    public EmpleadoService(EmpleadoRepository empleadoRepository) {
        this.empleadoRepository = empleadoRepository;
    }

    /**
     * Extrae lista de empleados de BD
     *
     * @return
     */
    @Override
    public List<EmpleadoDto> getAllEmployees() {
        List<Empleado> employees = empleadoRepository.findAll();
        List<EmpleadoDto> employeesL = new ArrayList<>();

        employees.forEach(emp -> {
            employeesL.add(employeeEntityToDto(emp));
        });

        return employeesL;
    }

    /**
     * Inserta lista de empleados en tabla empleado
     *
     * @param empleados
     * @return lista de empleados insertados
     */
    @Override
    public List<EmpleadoDto> createEmployees(List<EmpleadoR> empleados) {

        List<Empleado> employeesL = new ArrayList<>();
        empleados.forEach(emp -> {
            employeesL.add(employeeRToEntity(emp));
        });

        List<Empleado> finalEmployees = empleadoRepository.saveAll(employeesL);
        List<EmpleadoDto> employeesDto = new ArrayList<>();

        finalEmployees.forEach(empE -> {
            employeesDto.add(employeeEntityToDto(empE));
        });

        return employeesDto;

    }

    /**
     * Actualiza empleado en BD
     *
     * @param id
     * @param empleado
     * @return empleado actualizado
     */
    @Override
    public EmpleadoDto updateEmployee(Integer id, EmpleadoR empleado) {

        new EmpleadoException("No existe el empleado con el id dado", "100");

        EmpleadoDto empleadoDto = empleadoRepository.findById(id)
                .map(empE -> {
                    empE.setPrimerNombre(empleado.primerNombre());
                    empE.setSegundoNombre(empleado.segundoNombre());
                    empE.setApellidoPaterno(empleado.apellidoPaterno());
                    empE.setApellidoMaterno(empleado.apellidoMaterno());
                    empE.setEdad(empleado.edad());
                    empE.setSexo(empleado.sexo());
                    empE.setFechaNacimiento(empleado.fechaNacimiento());
                    empE.setPuesto(empleado.puesto());
                    empleadoRepository.save(empE);
                    return employeeEntityToDto(empE);
                })
                //.orElse(new EmpleadoDto());
                .orElseThrow(() -> new EmpleadoException("No existe el empleado con el id dado", "1001"));
        return empleadoDto;

    }

    /**
     * Elimina empleado de BD
     *
     * @param id
     */
    @Override
    public void deleteEmployee(Integer id) {
        empleadoRepository.deleteById(id);
    }

    private EmpleadoDto employeeEntityToDto(Empleado empleado) {
        EmpleadoDto empleadoDto = EmpleadoDto.builder()
                .id(empleado.getId())
                .primerNombre(empleado.getPrimerNombre())
                .segundoNombre(empleado.getSegundoNombre())
                .apellidoPaterno(empleado.getApellidoPaterno())
                .apellidoMaterno(empleado.getApellidoMaterno())
                .edad(empleado.getEdad())
                .sexo(empleado.getSexo())
                .fechaNacimiento(empleado.getFechaNacimiento())
                .puesto(empleado.getPuesto())
                .build();

        return empleadoDto;
    }

    private Empleado employeeRToEntity(EmpleadoR empleado) {
        Empleado empleadoE = Empleado.builder()
                .id(empleado.id())
                .primerNombre(empleado.primerNombre())
                .segundoNombre(empleado.segundoNombre())
                .apellidoPaterno(empleado.apellidoPaterno())
                .apellidoMaterno(empleado.apellidoMaterno())
                .edad(empleado.edad())
                .sexo(empleado.sexo())
                .fechaNacimiento(empleado.fechaNacimiento())
                .puesto(empleado.puesto())
                .build();

        return empleadoE;
    }

}
