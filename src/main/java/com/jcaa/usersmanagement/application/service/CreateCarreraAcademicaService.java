package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.CreateCarreraAcademicaUseCase;
import com.jcaa.usersmanagement.application.port.out.SaveCarreraAcademicaPort;
import com.jcaa.usersmanagement.application.service.dto.command.CreateCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.mapper.CarreraAcademicaApplicationMapper;
import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import java.util.Set;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateCarreraAcademicaService implements CreateCarreraAcademicaUseCase {

    private final SaveCarreraAcademicaPort saveCarreraAcademicaPort;
    private final Validator validator;

    @Override
    public CarreraAcademicaModel execute(final CreateCarreraAcademicaCommand command) {
        validateCommand(command);

        final CarreraAcademicaModel carreraAcademicaToSave =
                CarreraAcademicaApplicationMapper.fromCreateCommandToModel(command);

        return saveCarreraAcademicaPort.save(carreraAcademicaToSave);
    }

    private void validateCommand(final CreateCarreraAcademicaCommand command) {
        final Set<ConstraintViolation<CreateCarreraAcademicaCommand>> violations =
                validator.validate(command);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}