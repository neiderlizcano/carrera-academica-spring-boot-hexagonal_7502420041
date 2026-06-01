package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import java.math.BigDecimal;

public record UpdateCarreraAcademicaRestRequest(
        @NotBlank(message = "nombre must not be blank")
        @Size(max = 120, message = "nombre must not exceed 120 characters")
        String nombre,

        @NotNull(message = "numCreditos must not be null")
        @Positive(message = "numCreditos must be greater than zero")
        Integer numCreditos,

        @NotNull(message = "numAsignaturas must not be null")
        @Positive(message = "numAsignaturas must be greater than zero")
        Integer numAsignaturas,

        @NotNull(message = "numSemestres must not be null")
        @Positive(message = "numSemestres must be greater than zero")
        Integer numSemestres,

        @NotBlank(message = "nivelFormacion must not be blank")
        String nivelFormacion,

        @NotBlank(message = "titulo must not be blank")
        String titulo,

        @NotNull(message = "valorSemestre must not be null")
        @DecimalMin(value = "0.0", message = "valorSemestre must be greater than or equal to zero")
        BigDecimal valorSemestre,

        @NotBlank(message = "universidad must not be blank")
        String universidad,

        @NotNull(message = "esAcreditada must not be null")
        Boolean esAcreditada,

        String perfiles,

        @NotBlank(message = "areaConocimiento must not be blank")
        String areaConocimiento
) {}