package com.jcaa.usersmanagement.domain.model;

import java.math.BigDecimal;

public class CarreraAcademicaModel {

    private Long id;
    private String nombre;
    private Integer numCreditos;
    private Integer numAsignaturas;
    private Integer numSemestres;
    private String nivelFormacion;
    private String titulo;
    private BigDecimal valorSemestre;
    private String universidad;
    private Boolean esAcreditada;
    private String perfiles;
    private String areaConocimiento;

    public CarreraAcademicaModel(
            Long id,
            String nombre,
            Integer numCreditos,
            Integer numAsignaturas,
            Integer numSemestres,
            String nivelFormacion,
            String titulo,
            BigDecimal valorSemestre,
            String universidad,
            Boolean esAcreditada,
            String perfiles,
            String areaConocimiento
    ) {
        validarNombre(nombre);
        validarNumeroPositivo(numCreditos, "El número de créditos debe ser mayor que cero.");
        validarNumeroPositivo(numAsignaturas, "El número de asignaturas debe ser mayor que cero.");
        validarNumeroPositivo(numSemestres, "El número de semestres debe ser mayor que cero.");
        validarTextoObligatorio(nivelFormacion, "El nivel de formación es obligatorio.");
        validarTextoObligatorio(titulo, "El título es obligatorio.");
        validarValorSemestre(valorSemestre);
        validarTextoObligatorio(universidad, "La universidad es obligatoria.");
        validarTextoObligatorio(areaConocimiento, "El área de conocimiento es obligatoria.");

        this.id = id;
        this.nombre = nombre.trim();
        this.numCreditos = numCreditos;
        this.numAsignaturas = numAsignaturas;
        this.numSemestres = numSemestres;
        this.nivelFormacion = nivelFormacion.trim();
        this.titulo = titulo.trim();
        this.valorSemestre = valorSemestre;
        this.universidad = universidad.trim();
        this.esAcreditada = esAcreditada != null ? esAcreditada : Boolean.FALSE;
        this.perfiles = perfiles != null ? perfiles.trim() : "";
        this.areaConocimiento = areaConocimiento.trim();
    }

    public void actualizarDatos(
            String nombre,
            Integer numCreditos,
            Integer numAsignaturas,
            Integer numSemestres,
            String nivelFormacion,
            String titulo,
            BigDecimal valorSemestre,
            String universidad,
            Boolean esAcreditada,
            String perfiles,
            String areaConocimiento
    ) {
        validarNombre(nombre);
        validarNumeroPositivo(numCreditos, "El número de créditos debe ser mayor que cero.");
        validarNumeroPositivo(numAsignaturas, "El número de asignaturas debe ser mayor que cero.");
        validarNumeroPositivo(numSemestres, "El número de semestres debe ser mayor que cero.");
        validarTextoObligatorio(nivelFormacion, "El nivel de formación es obligatorio.");
        validarTextoObligatorio(titulo, "El título es obligatorio.");
        validarValorSemestre(valorSemestre);
        validarTextoObligatorio(universidad, "La universidad es obligatoria.");
        validarTextoObligatorio(areaConocimiento, "El área de conocimiento es obligatoria.");

        this.nombre = nombre.trim();
        this.numCreditos = numCreditos;
        this.numAsignaturas = numAsignaturas;
        this.numSemestres = numSemestres;
        this.nivelFormacion = nivelFormacion.trim();
        this.titulo = titulo.trim();
        this.valorSemestre = valorSemestre;
        this.universidad = universidad.trim();
        this.esAcreditada = esAcreditada != null ? esAcreditada : Boolean.FALSE;
        this.perfiles = perfiles != null ? perfiles.trim() : "";
        this.areaConocimiento = areaConocimiento.trim();
    }

    private void validarNombre(String nombre) {
        validarTextoObligatorio(nombre, "El nombre de la carrera es obligatorio.");

        if (nombre.trim().length() > 120) {
            throw new IllegalArgumentException("El nombre de la carrera no puede superar los 120 caracteres.");
        }
    }

    private void validarTextoObligatorio(String valor, String mensaje) {
        if (valor == null || valor.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    private void validarNumeroPositivo(Integer valor, String mensaje) {
        if (valor == null || valor <= 0) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    private void validarValorSemestre(BigDecimal valorSemestre) {
        if (valorSemestre == null || valorSemestre.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El valor del semestre no puede ser negativo.");
        }
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getNumCreditos() {
        return numCreditos;
    }

    public Integer getNumAsignaturas() {
        return numAsignaturas;
    }

    public Integer getNumSemestres() {
        return numSemestres;
    }

    public String getNivelFormacion() {
        return nivelFormacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public BigDecimal getValorSemestre() {
        return valorSemestre;
    }

    public String getUniversidad() {
        return universidad;
    }

    public Boolean getEsAcreditada() {
        return esAcreditada;
    }

    public String getPerfiles() {
        return perfiles;
    }

    public String getAreaConocimiento() {
        return areaConocimiento;
    }
}