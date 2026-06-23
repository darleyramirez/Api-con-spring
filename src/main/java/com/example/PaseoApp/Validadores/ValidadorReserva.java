package com.example.PaseoApp.validadores;

import com.example.PaseoApp.models.Reserva;

public interface ValidadorReserva {
    void validarNuevaReserva(Reserva datos);

    void validarDatosModificacion(Reserva datos);
}
