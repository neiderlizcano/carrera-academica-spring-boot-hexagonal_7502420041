package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.UpdateCarreraAcademicaCommand;
import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface UpdateCarreraAcademicaUseCase {
    CarreraAcademicaModel execute(@NotNull @Valid UpdateCarreraAcademicaCommand command);
}