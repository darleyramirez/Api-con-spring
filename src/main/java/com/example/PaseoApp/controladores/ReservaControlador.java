package com.example.PaseoApp.controladores;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
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
    // por cada servicio ofrecido, configuro una funcion controladora
    @Autowired
    ReservaServicio servicio;

    // funcion para coontrolar el guardado
    @PostMapping
    public ResponseEntity<Reserva> controlarGuardado(@RequestBody Reserva datos){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.servicio.guardarReservaEnBD(datos));
    }
    // funcion para controlar las modificaciones
    @PutMapping("/{id}")
    public ResponseEntity<Reserva>controlarmodificado(@RequestBody Reserva datos, @PathVariable UUID id){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.servicio.modificarReservaEnBD(id, datos));
    }
    // funcion para controlar el borrado
    @DeleteMapping("/{id}")
    public ResponseEntity<?>controlarBorrado(UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.eliminarenBD(id));
    }
    // funcion para controlar el listar
    @GetMapping
    public ResponseEntity<?>controlarListar(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.servicio.buscarReservaEnBD());
    }
}
