package com.example.test.controller;

import com.example.test.dtos.EmpleadoDto;
import com.example.test.records.EmpleadoR;
import com.example.test.service.IEmpleadoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import java.time.LocalDate;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(EmpleadoController.class)
class EmpleadoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockitoBean
    private IEmpleadoService empleadoService;

    private EmpleadoDto empleadoDto;
    private EmpleadoR empleadoR;

    @BeforeEach
    void setUp() {
        empleadoDto = EmpleadoDto.builder()
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
                (short)30,
                "M",
                LocalDate.of(1996, 5, 12),
                "Tester"
        );
    }

    @Test
    void getAllEmployees_HappyPath_ReturnsOk() throws Exception {
        when(empleadoService.getAllEmployees()).thenReturn(List.of(empleadoDto));

        mockMvc.perform(get("/employee"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].primerNombre").value("Jaime"))
                .andExpect(jsonPath("$[0].puesto").value("Tester"));

        verify(empleadoService, times(1)).getAllEmployees();
    }

    @Test
    void createEmployees_HappyPath_ReturnsOk() throws Exception {
        when(empleadoService.createEmployees(any())).thenReturn(List.of(empleadoDto));

        mockMvc.perform(post("/employee")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(List.of(empleadoR))))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$[0].id").value(1))
                .andExpect(jsonPath("$[0].primerNombre").value("Jaime"));

        verify(empleadoService, times(1)).createEmployees(any());
    }

    @Test
    void updateEmployee_HappyPath_ReturnsOk() throws Exception {
        Integer id = 1;
        when(empleadoService.updateEmployee(eq(id), any(EmpleadoR.class))).thenReturn(empleadoDto);

        mockMvc.perform(put("/employee/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(empleadoR)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.primerNombre").value("Jaime"));

        verify(empleadoService, times(1)).updateEmployee(eq(id), any(EmpleadoR.class));
    }

    @Test
    void deleteEmployee_HappyPath_ReturnsOk() throws Exception {
        Integer id = 1;
        doNothing().when(empleadoService).deleteEmployee(id);

        mockMvc.perform(delete("/employee/{id}", id))
                .andExpect(status().isOk());

        verify(empleadoService, times(1)).deleteEmployee(id);
    }
}
