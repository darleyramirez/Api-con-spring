package com.example.PaseoApp.models;

import jakarta.persistence.*;

import java.util.UUID;


@Entity
@Table (name = "Usuarios")
public class Usuario {
    private String nombres;
    private String correo;
    private String contraseña;
    private String rol;
    public Usuario() {
    }

    public Usuario(UUID id, String nombres, String correo, String contraseña, String rol) {
        this.id = id;
        this.nombres = nombres;
        this.correo = correo;
        this.contraseña = contraseña;
        this.rol = rol;
    }

    @Id
    @GeneratedValue (strategy = GenerationType.UUID)

    private UUID id;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }


}
