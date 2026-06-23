package com.example.PaseoApp.Validadores;

import com.example.PaseoApp.models.Reserva;

public interface ValidadorReserva {
    void validarNuevaReserva(Reserva datos);

    void validarDatosModificacion(Reserva datos);
}
