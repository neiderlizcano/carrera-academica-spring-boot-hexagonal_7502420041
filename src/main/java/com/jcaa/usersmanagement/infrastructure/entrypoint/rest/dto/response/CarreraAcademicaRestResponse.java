package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response;

import java.math.BigDecimal;

public record CarreraAcademicaRestResponse(
        Long id,
        String nombre,
        Integer numCreditos,
        Integer numAsignaturas,
        Integer numSemestres,
        String nivelFormacion,
        String titulo,
        BigDecimal valorSemestre,
        String universidad,
        Boolean esAcreditada,
        String perfiles,
        String areaConocimiento
) {}