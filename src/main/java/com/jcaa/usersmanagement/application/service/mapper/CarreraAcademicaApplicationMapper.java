package com.jcaa.usersmanagement.application.service.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetCarreraAcademicaByIdQuery;
import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CarreraAcademicaApplicationMapper {

    public CarreraAcademicaModel fromCreateCommandToModel(
            final CreateCarreraAcademicaCommand command) {
        return new CarreraAcademicaModel(
                null,
                command.nombre(),
                command.numCreditos(),
                command.numAsignaturas(),
                command.numSemestres(),
                command.nivelFormacion(),
                command.titulo(),
                command.valorSemestre(),
                command.universidad(),
                command.esAcreditada(),
                command.perfiles(),
                command.areaConocimiento());
    }

    public CarreraAcademicaModel fromUpdateCommandToModel(
            final UpdateCarreraAcademicaCommand command) {
        return new CarreraAcademicaModel(
                command.id(),
                command.nombre(),
                command.numCreditos(),
                command.numAsignaturas(),
                command.numSemestres(),
                command.nivelFormacion(),
                command.titulo(),
                command.valorSemestre(),
                command.universidad(),
                command.esAcreditada(),
                command.perfiles(),
                command.areaConocimiento());
    }

    public Long fromGetByIdQueryToId(final GetCarreraAcademicaByIdQuery query) {
        return query.id();
    }

    public Long fromDeleteCommandToId(final DeleteCarreraAcademicaCommand command) {
        return command.id();
    }
}