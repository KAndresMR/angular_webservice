package com.jakarta.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "usuarios") // Nombre de la tabla en PostgreSQL
public class User {

    @Id
    private String cedula;
    private String nombre;
    private double consumo;
    private double deuda;

    public User() {}

    public User(String cedula, String nombre, double consumo, double deuda) {
        this.cedula = cedula;
        this.nombre = nombre;
        this.consumo = consumo;
        this.deuda = deuda;
    }
    
    // Getters y setters

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getConsumo() {
        return consumo;
    }

    public void setConsumo(double consumo) {
        this.consumo = consumo;
    }

    public double getDeuda() {
        return deuda;
    }

    public void setDeuda(double deuda) {
        this.deuda = deuda;
    }

}
