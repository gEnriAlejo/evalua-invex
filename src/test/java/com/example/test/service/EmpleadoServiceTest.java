package com.example.test.service;

import com.example.test.common.exception.EmpleadoException;
import com.example.test.dtos.EmpleadoDto;
import com.example.test.records.EmpleadoR;
import com.example.test.repository.EmpleadoRepository;
import com.example.test.repository.model.Empleado;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmpleadoServiceTest {

    @Mock
    private EmpleadoRepository empleadoRepository;

    @InjectMocks
    private EmpleadoService empleadoService;

    private Empleado empleadoEntity;
    private EmpleadoR empleadoR;

    @BeforeEach
    void setUp() {
        empleadoEntity = Empleado.builder()
                .id(1)
                .primerNombre("Jaime")
                .segundoNombre("Carlos")
                .apellidoPaterno("Pérez")
                .apellidoMaterno("Garcia")
                .edad((short)30)
                .sexo("M")
                .fechaNacimiento(LocalDate.of(1996, 5, 12))
                .puesto("Tester")
                .build();

        empleadoR = new EmpleadoR(
                1,
                "Jaime",
                "Carlos",
                "Pérez",
                "Garcia",
                (short) 30,
                "M",
                LocalDate.of(1996, 5, 12),
                "Tester"
        );
    }

    @Test
    void getAllEmployees_HappyPath_Success() {
        when(empleadoRepository.findAll()).thenReturn(List.of(empleadoEntity));

        List<EmpleadoDto> resultado = empleadoService.getAllEmployees();

        assertNotNull(resultado);
        assertFalse(resultado.isEmpty());
        assertEquals(1, resultado.size());
        assertEquals("Jaime", resultado.get(0).getPrimerNombre()); // Si EmpleadoDto es Record, usa gettery, si es Lombok, getPrimerNombre()
        verify(empleadoRepository, times(1)).findAll();
    }

    @Test
    void createEmployees_HappyPath_Success() {
        when(empleadoRepository.saveAll(any())).thenReturn(List.of(empleadoEntity));

        List<EmpleadoDto> resultado = empleadoService.createEmployees(List.of(empleadoR));

        assertNotNull(resultado);
        assertEquals(1, resultado.size());
        assertEquals("Tester", resultado.get(0).getPuesto());
        verify(empleadoRepository, times(1)).saveAll(any());
    }

    @Test
    @DisplayName("Debería actualizar un empleado existente con éxito")
    void updateEmployee_HappyPath_Success() {
        when(empleadoRepository.findById(1)).thenReturn(Optional.of(empleadoEntity));
        when(empleadoRepository.save(any(Empleado.class))).thenReturn(empleadoEntity);

        EmpleadoDto resultado = empleadoService.updateEmployee(1, empleadoR);

        assertNotNull(resultado);
        assertEquals("Jaime", resultado.getPrimerNombre());
        verify(empleadoRepository, times(1)).findById(1);
        verify(empleadoRepository, times(1)).save(any(Empleado.class));
    }

    @Test
    void updateEmployee_NotFound_ExceptionIsThrown() {
        when(empleadoRepository.findById(99)).thenReturn(Optional.empty());

        EmpleadoR nuevoR = new EmpleadoR(99, "Karla",
                null, "Lopez", null,
                (short)25, "F", LocalDate.now(), "QA");

        assertThrows(EmpleadoException.class, () -> {
            empleadoService.updateEmployee(99, nuevoR);
        });

        verify(empleadoRepository, times(1)).findById(99);
        verify(empleadoRepository, never()).save(any(Empleado.class));
    }

    @Test
    void deleteEmployee_HappyPath_Success() {
        doNothing().when(empleadoRepository).deleteById(1);
        empleadoService.deleteEmployee(1);
        verify(empleadoRepository, times(1)).deleteById(1);
    }
}
