package com.example.test.repository.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Empleado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Integer id;
    @Column
	private String primerNombre;
    @Column
    private String segundoNombre;
    @Column
    private String apellidoPaterno;
    @Column
    private String apellidoMaterno;
    @Column
    private Short edad;
    @Column
    private String sexo;
    @Column
    private LocalDate fechaNacimiento;
    @Column
    private String puesto;

}
