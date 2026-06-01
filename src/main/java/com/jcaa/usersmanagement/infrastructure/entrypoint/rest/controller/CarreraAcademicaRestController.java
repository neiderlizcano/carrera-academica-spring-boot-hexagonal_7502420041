package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.application.port.in.CreateCarreraAcademicaUseCase;
import com.jcaa.usersmanagement.application.port.in.DeleteCarreraAcademicaUseCase;
import com.jcaa.usersmanagement.application.port.in.GetAllCarrerasAcademicasUseCase;
import com.jcaa.usersmanagement.application.port.in.GetCarreraAcademicaByIdUseCase;
import com.jcaa.usersmanagement.application.port.in.UpdateCarreraAcademicaUseCase;
import com.jcaa.usersmanagement.application.service.dto.command.CreateCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.DeleteCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.dto.command.UpdateCarreraAcademicaCommand;
import com.jcaa.usersmanagement.application.service.dto.query.GetCarreraAcademicaByIdQuery;
import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateCarreraAcademicaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateCarreraAcademicaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.CarreraAcademicaRestResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.mapper.CarreraAcademicaRestMapper;
import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/carreras-academicas")
@RequiredArgsConstructor
public class CarreraAcademicaRestController implements CarreraAcademicaRestControllerDocs {

    private final CreateCarreraAcademicaUseCase createCarreraAcademicaUseCase;
    private final UpdateCarreraAcademicaUseCase updateCarreraAcademicaUseCase;
    private final DeleteCarreraAcademicaUseCase deleteCarreraAcademicaUseCase;
    private final GetCarreraAcademicaByIdUseCase getCarreraAcademicaByIdUseCase;
    private final GetAllCarrerasAcademicasUseCase getAllCarrerasAcademicasUseCase;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CarreraAcademicaRestResponse create(
            @Valid @RequestBody final CreateCarreraAcademicaRestRequest request) {
        final CreateCarreraAcademicaCommand command =
                CarreraAcademicaRestMapper.toCreateCommand(request);
        final CarreraAcademicaModel carreraAcademica = createCarreraAcademicaUseCase.execute(command);
        return CarreraAcademicaRestMapper.toResponse(carreraAcademica);
    }

    @GetMapping
    public List<CarreraAcademicaRestResponse> getAll() {
        return CarreraAcademicaRestMapper.toResponseList(getAllCarrerasAcademicasUseCase.execute());
    }

    @GetMapping("/{id}")
    public CarreraAcademicaRestResponse getById(@PathVariable final Long id) {
        final GetCarreraAcademicaByIdQuery query = CarreraAcademicaRestMapper.toGetByIdQuery(id);
        final CarreraAcademicaModel carreraAcademica = getCarreraAcademicaByIdUseCase.execute(query);
        return CarreraAcademicaRestMapper.toResponse(carreraAcademica);
    }

    @PutMapping("/{id}")
    public CarreraAcademicaRestResponse update(
            @PathVariable final Long id,
            @Valid @RequestBody final UpdateCarreraAcademicaRestRequest request) {
        final UpdateCarreraAcademicaCommand command =
                CarreraAcademicaRestMapper.toUpdateCommand(id, request);
        final CarreraAcademicaModel carreraAcademica = updateCarreraAcademicaUseCase.execute(command);
        return CarreraAcademicaRestMapper.toResponse(carreraAcademica);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable final Long id) {
        final DeleteCarreraAcademicaCommand command = CarreraAcademicaRestMapper.toDeleteCommand(id);
        deleteCarreraAcademicaUseCase.execute(command);
    }
}