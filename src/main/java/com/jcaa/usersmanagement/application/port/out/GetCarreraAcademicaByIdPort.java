package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import java.util.Optional;

public interface GetCarreraAcademicaByIdPort {
    Optional<CarreraAcademicaModel> getById(Long id);
}