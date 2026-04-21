package com.example.PaseoApp.Servicios;


import com.example.PaseoApp.models.Espacios;
import com.example.PaseoApp.repositorios.EspaciosRepositorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EspacioServicio {

    //Inyectandop una dependencia al respositorio usuario

    @Autowired
    private EspaciosRepositorios repositorioEspacio;

    public Espacios guardarEspacioEnBD(Espacios datos){

      if(datos == null){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Los datos no pueden ser nulos"
            );
        }    

    if(datos.getFoto() == null || datos.getFoto().isBlank()){
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "La foto es obligatoria"
        );
    }

   
    if(datos.getAforo() == null){
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "El aforo es obligatorio"
        );
    }

    if(datos.getAforo() <= 0){
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "El aforo debe ser mayor a 0"
        );
    }
    return repositorioEspacio.save(datos);
}

    public Espacios modificarEspacioEnBD(Espacios datos, UUID id){ 
        Optional<Espacios> espacioExistente = this.repositorioEspacio.findById(id);
        if(espacioExistente.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Espacio no encontrado"
            );
        }
        Espacios espacio = espacioExistente.get();
        espacio.setNombre(datos.getNombre());
        espacio.setFoto(datos.getFoto());
        espacio.setAforo(datos.getAforo());
        espacio.setDescripcion(datos.getDescripcion());
        return this.repositorioEspacio.save(espacio);
    }
    
    public boolean eliminarenBD(UUID id){ 
        Optional<Espacios> espacioExistente = this.repositorioEspacio.findById(id);
        if(espacioExistente.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Espacio no encontrado"
            );
        }
        this.repositorioEspacio.deleteById(id);
        return true;
    }
    
    public List<Espacios> buscarEspacioEnBD(){
        return this.repositorioEspacio.findAll();
    }
}
