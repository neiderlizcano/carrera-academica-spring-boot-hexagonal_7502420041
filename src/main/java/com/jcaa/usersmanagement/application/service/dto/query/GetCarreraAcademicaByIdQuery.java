package com.jcaa.usersmanagement.application.service.dto.query;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record GetCarreraAcademicaByIdQuery(
        @NotNull(message = "id must not be null")
        @Positive(message = "id must be greater than zero")
        Long id
) {

}