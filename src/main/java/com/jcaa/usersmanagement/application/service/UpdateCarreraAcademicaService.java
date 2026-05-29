package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.UpdateCarreraAcademicaUseCase;
import com.jcaa.usersmanagement.application.port.out.GetCarreraAcademicaByIdPort;
import com.jcaa.usersmanagement.application.port.out.UpdateCarreraAcademicaPort;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.mapper.CarreraAcademicaApplicationMapper;
import com.jcaa.usersmanagement.domain.exception.CarreraAcademicaNotFoundException;
import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateCarreraAcademicaService implements UpdateCarreraAcademicaUseCase {

    private final UpdateCarreraAcademicaPort updateCarreraAcademicaPort;
    private final GetCarreraAcademicaByIdPort getCarreraAcademicaByIdPort;
    private final Validator validator;

    @Override
    public CarreraAcademicaModel execute(final UpdateCarreraAcademicaCommand command) {
        validateCommand(command);

        ensureCarreraAcademicaExists(command.id());

        final CarreraAcademicaModel carreraAcademicaToUpdate =
                CarreraAcademicaApplicationMapper.fromUpdateCommandToModel(command);

        return updateCarreraAcademicaPort.update(carreraAcademicaToUpdate);
    }

    private void validateCommand(final UpdateCarreraAcademicaCommand command) {
        final Set<ConstraintViolation<UpdateCarreraAcademicaCommand>> violations =
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