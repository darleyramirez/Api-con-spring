package com.example.PaseoApp.Servicios;

import com.example.PaseoApp.models.Reserva;
import com.example.PaseoApp.repositorios.ReservaRepositorios;
import com.example.PaseoApp.validadores.ValidadorReserva;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservaServicioImpl implements ReservaServicio {

    private final ReservaRepositorios repositorioReserva;
    private final ValidadorReserva validadorReserva;

    public ReservaServicioImpl(ReservaRepositorios repositorioReserva, ValidadorReserva validadorReserva) {
        this.repositorioReserva = repositorioReserva;
        this.validadorReserva = validadorReserva;
    }

    @Override
    public Reserva guardarReservaEnBD(Reserva datos) {
        validadorReserva.validarNuevaReserva(datos);
        return this.repositorioReserva.save(datos);
    }

    @Override
    public Reserva modificarReservaEnBD(UUID id, Reserva datos) {
        if (datos == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de reserva obligatorios");
        }

        Reserva reservaExistente = this.repositorioReserva.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada"));

        validadorReserva.validarDatosModificacion(datos);

        if (!datos.getFecha().equals(reservaExistente.getFecha()) && this.repositorioReserva.findByFecha(datos.getFecha()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe una reserva en esa fecha");
        }

        reservaExistente.setFecha(datos.getFecha());
        reservaExistente.setTiempo(datos.getTiempo());

        return this.repositorioReserva.save(reservaExistente);
    }

    @Override
    public boolean eliminarReservaEnBD(UUID id) {
        Optional<Reserva> reservaExistente = this.repositorioReserva.findById(id);
        if (reservaExistente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Reserva no encontrada");
        }

        this.repositorioReserva.deleteById(id);
        return true;
    }

    @Override
    public List<Reserva> buscarReservaEnBD() {
        return this.repositorioReserva.findAll();
    }
}
