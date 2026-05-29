package com.jcaa.usersmanagement.application.port.out;

import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;

public interface SaveCarreraAcademicaPort {
    CarreraAcademicaModel save(CarreraAcademicaModel carreraAcademica);
}