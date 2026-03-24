package com.example.PaseoApp.models;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "Reserva")
public class Reserva {
    private UUID id;
    private Integer tiempo;
    private LocalDateTime fecha;


    public Reserva() {
    }

    public Reserva(UUID id, Integer tiempo, LocalDateTime fecha) {
        this.id = id;
        this.tiempo = tiempo;
        this.fecha = fecha;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)


    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getTiempo() {
        return tiempo;
    }

    public void setTiempo(Integer tiempo) {
        this.tiempo = tiempo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }


}
