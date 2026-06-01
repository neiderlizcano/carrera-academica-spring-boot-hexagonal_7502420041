package com.jcaa.usersmanagement.infrastructure.entrypoint.rest.controller;

import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.CreateCarreraAcademicaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.request.UpdateCarreraAcademicaRestRequest;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.ApiErrorResponse;
import com.jcaa.usersmanagement.infrastructure.entrypoint.rest.dto.response.CarreraAcademicaRestResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@Tag(
        name = "Carreras Académicas",
        description = "CRUDL de carreras académicas asignado para la actividad.")
public interface CarreraAcademicaRestControllerDocs {

    @Operation(
            summary = "Crear carrera académica",
            description = "Registra una nueva carrera académica en el sistema.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Carrera académica creada exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarreraAcademicaRestResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    CarreraAcademicaRestResponse create(
            @Valid @RequestBody CreateCarreraAcademicaRestRequest request);

    @Operation(
            summary = "Listar carreras académicas",
            description = "Retorna todas las carreras académicas registradas.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista obtenida exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            array =
                            @ArraySchema(
                                    schema = @Schema(implementation = CarreraAcademicaRestResponse.class)))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    List<CarreraAcademicaRestResponse> getAll();

    @Operation(
            summary = "Obtener carrera académica por ID",
            description = "Retorna una carrera académica específica por su identificador.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Carrera académica encontrada.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarreraAcademicaRestResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe una carrera académica con el ID proporcionado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    CarreraAcademicaRestResponse getById(
            @Parameter(description = "ID de la carrera académica.", example = "1", required = true)
            @PathVariable
            Long id);

    @Operation(
            summary = "Actualizar carrera académica",
            description = "Actualiza los datos de una carrera académica existente.")
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Carrera académica actualizada exitosamente.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = CarreraAcademicaRestResponse.class))),
            @ApiResponse(
                    responseCode = "400",
                    description = "Datos de entrada inválidos.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe una carrera académica con el ID proporcionado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    CarreraAcademicaRestResponse update(
            @Parameter(description = "ID de la carrera académica.", example = "1", required = true)
            @PathVariable
            Long id,
            @Valid @RequestBody UpdateCarreraAcademicaRestRequest request);

    @Operation(
            summary = "Eliminar carrera académica",
            description = "Elimina una carrera académica por su ID.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Carrera académica eliminada exitosamente."),
            @ApiResponse(
                    responseCode = "404",
                    description = "No existe una carrera académica con el ID proporcionado.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class))),
            @ApiResponse(
                    responseCode = "500",
                    description = "Error interno del servidor.",
                    content =
                    @Content(
                            mediaType = "application/json",
                            schema = @Schema(implementation = ApiErrorResponse.class)))
    })
    void delete(
            @Parameter(description = "ID de la carrera académica.", example = "1", required = true)
            @PathVariable
            Long id);
}