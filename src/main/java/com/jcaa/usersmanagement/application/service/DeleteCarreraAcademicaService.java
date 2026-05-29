package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.DeleteCarreraAcademicaUseCase;
import com.jcaa.usersmanagement.application.port.out.DeleteCarreraAcademicaPort;
import com.jcaa.usersmanagement.application.port.out.GetCarreraAcademicaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.mapper.CarreraAcademicaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.CarreraAcademicaNotFoundException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteCarreraAcademicaService implements DeleteCarreraAcademicaUseCase {

    private final DeleteCarreraAcademicaPort deleteCarreraAcademicaPort;
    private final GetCarreraAcademicaByIdPort getCarreraAcademicaByIdPort;
    private final Validator validator;

    @Override
    public void execute(final DeleteCarreraAcademicaCommand command) {
        validateCommand(command);

        final Long id = CarreraAcademicaApplicationMapper.fromDeleteCommandToId(command);

        ensureCarreraAcademicaExists(id);
        deleteCarreraAcademicaPort.deleteById(id);
    }

    private void validateCommand(final DeleteCarreraAcademicaCommand command) {
        final Set<ConstraintViolation<DeleteCarreraAcademicaCommand>> violations =
                validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }

    private void ensureCarreraAcademicaExists(final Long id) {
        getCarreraAcademicaByIdPort
                .getById(id)
                .orElseThrow(() -> CarreraAcademicaNotFoundException.becauseIdWasNotFound(id));
    }
}