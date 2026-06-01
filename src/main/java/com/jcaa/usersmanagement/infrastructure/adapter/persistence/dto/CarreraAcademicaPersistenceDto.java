package com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto;

import java.math.BigDecimal;

public record CarreraAcademicaPersistenceDto(
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
        String areaConocimiento,
        String createdAt,
        String updatedAt) {}