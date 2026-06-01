package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper;

import com.jcaa.usersmanagement.application.service.dto.command.CreateCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetCarreraAcademicaByIdQuery;
import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateCarreraAcademicaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateCarreraAcademicaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.CarreraAcademicaRestResponse;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CarreraAcademicaRestMapper {

    public CreateCarreraAcademicaCommand toCreateCommand(
            final CreateCarreraAcademicaRestRequest request) {
        return new CreateCarreraAcademicaCommand(
                request.nombre(),
                request.numCreditos(),
                request.numAsignaturas(),
                request.numSemestres(),
                request.nivelFormacion(),
                request.titulo(),
                request.valorSemestre(),
                request.universidad(),
                request.esAcreditada(),
                request.perfiles(),
                request.areaConocimiento());
    }

    public UpdateCarreraAcademicaCommand toUpdateCommand(
            final Long id, final UpdateCarreraAcademicaRestRequest request) {
        return new UpdateCarreraAcademicaCommand(
                id,
                request.nombre(),
                request.numCreditos(),
                request.numAsignaturas(),
                request.numSemestres(),
                request.nivelFormacion(),
                request.titulo(),
                request.valorSemestre(),
                request.universidad(),
                request.esAcreditada(),
                request.perfiles(),
                request.areaConocimiento());
    }

    public GetCarreraAcademicaByIdQuery toGetByIdQuery(final Long id) {
        return new GetCarreraAcademicaByIdQuery(id);
    }

    public DeleteCarreraAcademicaCommand toDeleteCommand(final Long id) {
        return new DeleteCarreraAcademicaCommand(id);
    }

    public CarreraAcademicaRestResponse toResponse(final CarreraAcademicaModel carreraAcademica) {
        return new CarreraAcademicaRestResponse(
                carreraAcademica.getId(),
                carreraAcademica.getNombre(),
                carreraAcademica.getNumCreditos(),
                carreraAcademica.getNumAsignaturas(),
                carreraAcademica.getNumSemestres(),
                carreraAcademica.getNivelFormacion(),
                carreraAcademica.getTitulo(),
                carreraAcademica.getValorSemestre(),
                carreraAcademica.getUniversidad(),
                carreraAcademica.getEsAcreditada(),
                carreraAcademica.getPerfiles(),
                carreraAcademica.getAreaConocimiento());
    }

    public List<CarreraAcademicaRestResponse> toResponseList(
            final List<CarreraAcademicaModel> carrerasAcademicas) {
        return carrerasAcademicas.stream().map(CarreraAcademicaRestMapper::toResponse).toList();
    }
}