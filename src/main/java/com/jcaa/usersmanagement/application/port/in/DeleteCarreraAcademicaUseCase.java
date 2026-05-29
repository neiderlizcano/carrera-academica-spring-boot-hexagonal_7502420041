package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.application.service.dto.command.DeleteCarreraAcademicaCommand;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;

public interface DeleteCarreraAcademicaUseCase {
    void execute(@NotNull @Valid DeleteCarreraAcademicaCommand command);
}