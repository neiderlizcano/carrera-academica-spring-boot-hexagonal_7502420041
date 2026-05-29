package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import java.util.List;

public interface GetAllCarrerasAcademicasPort {
    List<CarreraAcademicaModel> getAll();
}