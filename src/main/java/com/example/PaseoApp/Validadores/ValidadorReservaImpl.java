package com.example.PaseoApp.Validadores;

import com.example.PaseoApp.Enums.FranjaHoraria;
import com.example.PaseoApp.models.Reserva;
import com.example.PaseoApp.repositorios.ReservaRepositorios;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Component
public class ValidadorReservaImpl implements ValidadorReserva {

    private final ReservaRepositorios reservaRepositorios;

    public ValidadorReservaImpl(ReservaRepositorios reservaRepositorios) {
        this.reservaRepositorios = reservaRepositorios;
    }

    @Override
    public void validarNuevaReserva(Reserva datos) {
        validarReservaNoNula(datos);
        validarFechaPresenteOFutura(datos.getFecha());
        validarTiempoNoNulo(datos.getTiempo());
        validarFechaNoDuplicada(datos.getFecha());
    }

    @Override
    public void validarDatosModificacion(Reserva datos) {
        validarReservaNoNula(datos);
        validarFechaPresenteOFutura(datos.getFecha());
        validarTiempoNoNulo(datos.getTiempo());
    }

    private void validarReservaNoNula(Reserva datos) {
        if (datos == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los datos de la reserva son obligatorios");
        }
    }

    private void validarFechaPresenteOFutura(LocalDateTime fecha) {
        if (fecha == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha es obligatoria");
        }

        if (fecha.isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha no puede ser en el pasado");
        }
    }

    private void validarTiempoNoNulo(FranjaHoraria tiempo) {
        if (tiempo == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La franja horaria es obligatoria");
        }
    }

    private void validarFechaNoDuplicada(LocalDateTime fecha) {
        if (fecha == null) {
            return;
        }

        if (reservaRepositorios.findByFecha(fecha).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una reserva en esa fecha");
        }
    }
}
