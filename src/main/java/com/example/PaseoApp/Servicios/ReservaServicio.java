package com.example.PaseoApp.Servicios;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import com.example.PaseoApp.models.Reserva;
import com.example.PaseoApp.repositorios.ReservaRepositorios;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservaServicio {

    @Autowired
    private ReservaRepositorios repositorioReserva;

    public Reserva guardarReservaEnBD(Reserva datos){
        validarReserva(datos);
        return this.repositorioReserva.save(datos); 
    }

    public Reserva modificarReservaEnBD(UUID id, Reserva datos){ 

        Reserva reservaExistente = this.repositorioReserva.findById(id)
            .orElseThrow(() -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Reserva no encontrada"
            ));

        validarReserva(datos);

        reservaExistente.setFecha(datos.getFecha());
        reservaExistente.setTiempo(datos.getTiempo());

        return this.repositorioReserva.save(reservaExistente);
    }

    private void validarReserva(Reserva datos){
        if(datos.getFecha() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La fecha es obligatoria");
        }

        if(datos.getFecha().isBefore(java.time.LocalDateTime.now())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                 "La fecha no puede ser en el pasado");
        }

        if(datos.getTiempo() == null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "El tiempo es obligatorio");
        }

        if(datos.getTiempo() <= 0){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, 
                "El tiempo debe ser mayor a 0");
        }
    }
      public boolean eliminarenBD(UUID id){ 
        Optional<Reserva> reservaExistente = this.repositorioReserva.findById(id);
        if(reservaExistente.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Reserva no encontrada"
            );
        }
        this.repositorioReserva.deleteById(id);
        return true;
    }
  

    public List<Reserva> buscarReservaEnBD(){ 
        List<Reserva> ReservaEncontrados = this.repositorioReserva.findAll();
        return  ReservaEncontrados;
    }
}