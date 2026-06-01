package com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper;

import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.CarreraAcademicaPersistenceDto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CarreraAcademicaPersistenceMapper {

    public CarreraAcademicaPersistenceDto fromModelToDto(
            final CarreraAcademicaModel carreraAcademica) {
        return new CarreraAcademicaPersistenceDto(
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
                carreraAcademica.getAreaConocimiento(),
                null,
                null);
    }

    public CarreraAcademicaModel fromResultSetToModel(final ResultSet resultSet)
            throws SQLException {
        return new CarreraAcademicaModel(
                resultSet.getLong("id"),
                resultSet.getString("nombre"),
                resultSet.getInt("num_creditos"),
                resultSet.getInt("num_asignaturas"),
                resultSet.getInt("num_semestres"),
                resultSet.getString("nivel_formacion"),
                resultSet.getString("titulo"),
                resultSet.getBigDecimal("valor_semestre"),
                resultSet.getString("universidad"),
                resultSet.getBoolean("es_acreditada"),
                resultSet.getString("perfiles"),
                resultSet.getString("area_conocimiento"));
    }

    public List<CarreraAcademicaModel> fromResultSetToModelList(final ResultSet resultSet)
            throws SQLException {
        final List<CarreraAcademicaModel> carrerasAcademicas = new ArrayList<>();

        while (resultSet.next()) {
            carrerasAcademicas.add(fromResultSetToModel(resultSet));
        }

        return carrerasAcademicas;
    }
}