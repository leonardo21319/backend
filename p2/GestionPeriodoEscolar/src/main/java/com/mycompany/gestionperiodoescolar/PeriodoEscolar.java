package com.mycompany.gestionperiodoescolar;

import java.util.Date;

public class PeriodoEscolar {

    private int idPeriodoEscolar;
    private String nombre;
    private Date fechaInicio;
    private Date fechaTermino;
    private boolean status;

    public PeriodoEscolar() {
    }

    public PeriodoEscolar(int idPeriodoEscolar, String nombre, Date fechaInicio, Date fechaTermino, boolean status) {
        this.idPeriodoEscolar = idPeriodoEscolar;
        this.nombre = nombre;
        this.fechaInicio = fechaInicio;
        this.fechaTermino = fechaTermino;
        this.status = status;
    }

    public int getIdPeriodoEscolar() {
        return idPeriodoEscolar;
    }

    public void setIdPeriodoEscolar(int idPeriodoEscolar) {
        this.idPeriodoEscolar = idPeriodoEscolar;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(Date fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PeriodoEscolar{" +
                "idPeriodoEscolar=" + idPeriodoEscolar +
                ", nombre='" + nombre + '\'' +
                ", fechaInicio=" + fechaInicio +
                ", fechaTermino=" + fechaTermino +
                ", status=" + status +
                '}';
    }
}
