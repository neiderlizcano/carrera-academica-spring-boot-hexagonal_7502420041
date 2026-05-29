package com.jcaa.usersmanagement.domain.exception;

public class CarreraAcademicaNotFoundException extends RuntimeException {

    private CarreraAcademicaNotFoundException(final String message) {
        super(message);
    }

    public static CarreraAcademicaNotFoundException becauseIdWasNotFound(final Long id) {
        return new CarreraAcademicaNotFoundException(
                "No se encontró una carrera académica con el id: " + id);
    }
}