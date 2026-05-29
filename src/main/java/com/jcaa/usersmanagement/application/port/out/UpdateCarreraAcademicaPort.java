package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;

public interface UpdateCarreraAcademicaPort {
    CarreraAcademicaModel update(CarreraAcademicaModel carreraAcademica);
}