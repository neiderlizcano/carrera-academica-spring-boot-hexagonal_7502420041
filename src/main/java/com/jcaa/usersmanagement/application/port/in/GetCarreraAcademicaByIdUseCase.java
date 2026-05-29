package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.query.GetCarreraAcademicaByIdQuery;
import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface GetCarreraAcademicaByIdUseCase {
    CarreraAcademicaModel execute(@NotNull @Valid GetCarreraAcademicaByIdQuery query);
}