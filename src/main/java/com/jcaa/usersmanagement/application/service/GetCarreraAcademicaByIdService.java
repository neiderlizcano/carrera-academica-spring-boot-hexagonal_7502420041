package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetCarreraAcademicaByIdUseCase;
import com.jcaa.usersmanagement.application.port.out.GetCarreraAcademicaByIdPort;
import com.jcaa.usersmanagement.application.service.dto.query.GetCarreraAcademicaByIdQuery;
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
public class GetCarreraAcademicaByIdService implements GetCarreraAcademicaByIdUseCase {

    private final GetCarreraAcademicaByIdPort getCarreraAcademicaByIdPort;
    private final Validator validator;

    @Override
    public CarreraAcademicaModel execute(final GetCarreraAcademicaByIdQuery query) {
        validateQuery(query);

        final Long id = CarreraAcademicaApplicationMapper.fromGetByIdQueryToId(query);

        return getCarreraAcademicaByIdPort
                .getById(id)
                .orElseThrow(() -> CarreraAcademicaNotFoundException.becauseIdWasNotFound(id));
    }

    private void validateQuery(final GetCarreraAcademicaByIdQuery query) {
        final Set<ConstraintViolation<GetCarreraAcademicaByIdQuery>> violations =
                validator.validate(query);

        if (!violations.isEmpty()) {
            throw new ConstraintViolationException(violations);
        }
    }
}