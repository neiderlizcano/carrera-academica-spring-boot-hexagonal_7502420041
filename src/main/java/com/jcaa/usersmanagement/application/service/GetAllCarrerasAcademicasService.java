package com.jcaa.usersmanagement.application.service;

import com.jcaa.usersmanagement.application.port.in.GetAllCarrerasAcademicasUseCase;
import com.jcaa.usersmanagement.application.port.out.GetAllCarrerasAcademicasPort;
import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetAllCarrerasAcademicasService implements GetAllCarrerasAcademicasUseCase {

    private final GetAllCarrerasAcademicasPort getAllCarrerasAcademicasPort;

    @Override
    public List<CarreraAcademicaModel> execute() {
        return getAllCarrerasAcademicasPort.getAll();
    }
}