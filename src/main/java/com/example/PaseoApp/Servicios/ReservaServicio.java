package com.example.PaseoApp.Servicios;

import com.example.PaseoApp.models.Reserva;

import java.util.List;
import java.util.UUID;

public interface ReservaServicio {

    Reserva guardarReservaEnBD(Reserva datos);

    Reserva modificarReservaEnBD(UUID id, Reserva datos);

    boolean eliminarReservaEnBD(UUID id);

    List<Reserva> buscarReservaEnBD();

}
