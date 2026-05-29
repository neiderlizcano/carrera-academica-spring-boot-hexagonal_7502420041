package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.CreateCarreraAcademicaCommand;
import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface CreateCarreraAcademicaUseCase {
    CarreraAcademicaModel execute(@NotNull @Valid CreateCarreraAcademicaCommand command);
}