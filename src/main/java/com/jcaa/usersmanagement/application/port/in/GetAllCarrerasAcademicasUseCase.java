package com.jcaa.usersmanagement.application.port.in;

import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import java.util.List;

public interface GetAllCarrerasAcademicasUseCase {
    List<CarreraAcademicaModel> execute();
}