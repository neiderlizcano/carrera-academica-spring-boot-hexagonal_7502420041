package com.jcaa.usersmanagement.infrastructure.adapter.persistence.repository;

import com.jcaa.usersmanagement.application.port.out.DeleteCarreraAcademicaPort;
import com.jcaa.usersmanagement.application.port.out.GetAllCarrerasAcademicasPort;
import com.jcaa.usersmanagement.application.port.out.GetCarreraAcademicaByIdPort;
import com.jcaa.usersmanagement.application.port.out.SaveCarreraAcademicaPort;
import com.jcaa.usersmanagement.application.port.out.UpdateCarreraAcademicaPort;
import com.jcaa.usersmanagement.domain.exception.CarreraAcademicaNotFoundException;
import com.jcaa.usersmanagement.domain.model.CarreraAcademicaModel;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.dto.CarreraAcademicaPersistenceDto;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.exception.PersistenceException;
import com.jcaa.usersmanagement.infrastructure.adapter.persistence.mapper.CarreraAcademicaPersistenceMapper;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;
import javax.sql.DataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
@RequiredArgsConstructor
public class CarreraAcademicaRepositoryMySQL
        implements SaveCarreraAcademicaPort,
        UpdateCarreraAcademicaPort,
        GetCarreraAcademicaByIdPort,
        GetAllCarrerasAcademicasPort,
        DeleteCarreraAcademicaPort {

    private static final String SQL_INSERT =
            "INSERT INTO carreras_academicas "
                    + "(nombre, num_creditos, num_asignaturas, num_semestres, nivel_formacion, "
                    + "titulo, valor_semestre, universidad, es_acreditada, perfiles, area_conocimiento, "
                    + "created_at, updated_at) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, NOW(), NOW())";

    private static final String SQL_UPDATE =
            "UPDATE carreras_academicas SET "
                    + "nombre = ?, num_creditos = ?, num_asignaturas = ?, num_semestres = ?, "
                    + "nivel_formacion = ?, titulo = ?, valor_semestre = ?, universidad = ?, "
                    + "es_acreditada = ?, perfiles = ?, area_conocimiento = ?, updated_at = NOW() "
                    + "WHERE id = ?";

    private static final String SQL_SELECT_BY_ID =
            "SELECT id, nombre, num_creditos, num_asignaturas, num_semestres, nivel_formacion, "
                    + "titulo, valor_semestre, universidad, es_acreditada, perfiles, area_conocimiento, "
                    + "created_at, updated_at "
                    + "FROM carreras_academicas "
                    + "WHERE id = ? LIMIT 1";

    private static final String SQL_SELECT_ALL =
            "SELECT id, nombre, num_creditos, num_asignaturas, num_semestres, nivel_formacion, "
                    + "titulo, valor_semestre, universidad, es_acreditada, perfiles, area_conocimiento, "
                    + "created_at, updated_at "
                    + "FROM carreras_academicas "
                    + "ORDER BY nombre ASC";

    private static final String SQL_DELETE =
            "DELETE FROM carreras_academicas "
                    + "WHERE id = ?";

    private final DataSource dataSource;

    @Override
    public CarreraAcademicaModel save(final CarreraAcademicaModel carreraAcademica) {
        final CarreraAcademicaPersistenceDto dto =
                CarreraAcademicaPersistenceMapper.fromModelToDto(carreraAcademica);

        final Long generatedId = executeSave(dto);
        return findByIdOrFail(generatedId);
    }

    @Override
    public CarreraAcademicaModel update(final CarreraAcademicaModel carreraAcademica) {
        final CarreraAcademicaPersistenceDto dto =
                CarreraAcademicaPersistenceMapper.fromModelToDto(carreraAcademica);

        executeUpdate(dto);
        return findByIdOrFail(dto.id());
    }

    @Override
    public Optional<CarreraAcademicaModel> getById(final Long id) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID)) {
            statement.setLong(1, id);

            final ResultSet resultSet = statement.executeQuery();

            if (!resultSet.next()) {
                return Optional.empty();
            }

            return Optional.of(CarreraAcademicaPersistenceMapper.fromResultSetToModel(resultSet));
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindByIdFailed(String.valueOf(id), exception);
        }
    }

    @Override
    public List<CarreraAcademicaModel> getAll() {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(SQL_SELECT_ALL)) {
            final ResultSet resultSet = statement.executeQuery();
            return CarreraAcademicaPersistenceMapper.fromResultSetToModelList(resultSet);
        } catch (final SQLException exception) {
            throw PersistenceException.becauseFindAllFailed(exception);
        }
    }

    @Override
    public void deleteById(final Long id) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(SQL_DELETE)) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseDeleteFailed(String.valueOf(id), exception);
        }
    }

    private Long executeSave(final CarreraAcademicaPersistenceDto dto) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement =
                     connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, dto.nombre());
            statement.setInt(2, dto.numCreditos());
            statement.setInt(3, dto.numAsignaturas());
            statement.setInt(4, dto.numSemestres());
            statement.setString(5, dto.nivelFormacion());
            statement.setString(6, dto.titulo());
            statement.setBigDecimal(7, dto.valorSemestre());
            statement.setString(8, dto.universidad());
            statement.setBoolean(9, dto.esAcreditada());
            statement.setString(10, dto.perfiles());
            statement.setString(11, dto.areaConocimiento());

            statement.executeUpdate();

            final ResultSet generatedKeys = statement.getGeneratedKeys();

            if (generatedKeys.next()) {
                return generatedKeys.getLong(1);
            }

            throw new IllegalStateException("No se pudo obtener el id generado de la carrera académica.");
        } catch (final SQLException exception) {
            throw PersistenceException.becauseSaveFailed("carrera_academica", exception);
        }
    }

    private void executeUpdate(final CarreraAcademicaPersistenceDto dto) {
        try (final Connection connection = dataSource.getConnection();
             final PreparedStatement statement = connection.prepareStatement(SQL_UPDATE)) {
            statement.setString(1, dto.nombre());
            statement.setInt(2, dto.numCreditos());
            statement.setInt(3, dto.numAsignaturas());
            statement.setInt(4, dto.numSemestres());
            statement.setString(5, dto.nivelFormacion());
            statement.setString(6, dto.titulo());
            statement.setBigDecimal(7, dto.valorSemestre());
            statement.setString(8, dto.universidad());
            statement.setBoolean(9, dto.esAcreditada());
            statement.setString(10, dto.perfiles());
            statement.setString(11, dto.areaConocimiento());
            statement.setLong(12, dto.id());

            statement.executeUpdate();
        } catch (final SQLException exception) {
            throw PersistenceException.becauseUpdateFailed(String.valueOf(dto.id()), exception);
        }
    }

    private CarreraAcademicaModel findByIdOrFail(final Long id) {
        return getById(id)
                .orElseThrow(() -> CarreraAcademicaNotFoundException.becauseIdWasNotFound(id));
    }
}