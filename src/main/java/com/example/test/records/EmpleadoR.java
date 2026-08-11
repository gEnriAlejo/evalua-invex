package com.example.test.records;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record EmpleadoR(Integer id,
                        @NotBlank(message = "El primer nombre no debe estar vacio")
                        String primerNombre,
                        String segundoNombre,
                        @NotBlank(message = "El apellido paterno no debe estar vacio")
                        String apellidoPaterno,
                        @NotBlank(message = "El apellido materno no debe estar vacio")
                        String apellidoMaterno,
                        @NotNull(message = "La edad no debe estar vacio")
                        Short edad,
                        @NotBlank(message = "La especificación del sexo no debe estar vacio")
                        String sexo,
                        @NotNull(message = "La fecha nacimiento no debe estar vacia")
                        LocalDate fechaNacimiento,
                        @NotBlank(message = "El puesto no debe estar vacio")
                        String puesto) {
}
