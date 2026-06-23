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

import com.example.PaseoApp.Servicios.EspacioServicio;
import com.example.PaseoApp.models.Espacios;

@RestController
@RequestMapping("/paseoapi/v1/espacios")
public class EspacioControlador {
    // por cada servicio ofrecido, configuro una funcion controladora
    private final EspacioServicio servicio;

    public EspacioControlador(EspacioServicio servicio) {
        this.servicio = servicio;
    }

    // funcion para controlar el guardado
    @PostMapping
    public ResponseEntity<Espacios> controlarGuardado(@RequestBody Espacios datos){
        return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(this.servicio.guardarEspacioEnBD(datos));
    }

    // funcion para controlar las modificaciones
    @PutMapping("/{id}")
    public ResponseEntity<Espacios> controlarModificado(@RequestBody Espacios datos, @PathVariable UUID id){
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.modificarEspacioEnBD(datos, id));
    }

    // funcion para controlar el borrado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> controlarBorrado(@PathVariable UUID id){
        this.servicio.eliminarEspacioEnBD(id);
        return ResponseEntity.noContent().build();
    }

    // funcion para controlar el listar
    @GetMapping
    public ResponseEntity<java.util.List<Espacios>> controlarListar(){
        return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(this.servicio.buscarEspaciosEnBD());
    }
}
