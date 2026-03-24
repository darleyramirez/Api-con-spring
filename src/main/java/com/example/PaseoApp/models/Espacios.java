package com.example.PaseoApp.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Espacios")

public class Espacios {
    private UUID id;
    private String nombre;
    private String foto;
    private Integer aforo;
    private String descripcion;
    public Espacios() {
    }

    public Espacios(UUID id, String nombre, String foto, Integer aforo, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.foto = foto;
        this.aforo = aforo;
        this.descripcion = descripcion;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Integer getAforo() {
        return aforo;
    }

    public void setAforo(Integer aforo) {
        this.aforo = aforo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

}
