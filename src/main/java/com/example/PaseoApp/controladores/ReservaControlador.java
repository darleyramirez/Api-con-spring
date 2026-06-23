package com.example.PaseoApp.controladores;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.PaseoApp.Servicios.ReservaServicio;
import com.example.PaseoApp.models.Reserva;

@RestController
@RequestMapping("/paseoapi/v1/reservas")
public class ReservaControlador {
    private final ReservaServicio servicio;

    public ReservaControlador(ReservaServicio servicio) {
        this.servicio = servicio;
    }

    @PostMapping
    public ResponseEntity<Reserva> controlarGuardado(@RequestBody Reserva datos){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.servicio.guardarReservaEnBD(datos));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Reserva> controlarModificado(@RequestBody Reserva datos, @PathVariable UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.modificarReservaEnBD(id, datos));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> controlarBorrado(@PathVariable UUID id){
        this.servicio.eliminarReservaEnBD(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<java.util.List<Reserva>> controlarListar(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarReservaEnBD());
    }
}
