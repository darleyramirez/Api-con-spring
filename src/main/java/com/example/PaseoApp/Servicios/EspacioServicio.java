package com.example.PaseoApp.Servicios;


import com.example.PaseoApp.models.Usuario;
import com.example.PaseoApp.repositorios.EspaciosRepositorios;
import com.example.PaseoApp.repositorios.UsuarioRepositorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class EspacioServicio {

    //Inyectandop una depndnecia al respositorio usuario

    @Autowired
    private EspaciosRepositorios espaciosRepositorios;

    public boolean guardarEspacioEnBD(EspaciosRepositorios datos){ return  false; }
    public boolean modificarEspacioEnBD(EspaciosRepositorios datos, UUID id){ return  false;}
    public boolean eliminarenBD(EspaciosRepositorios datos, UUID id){ return  false;}
    public boolean modificarEspacioEnBD(UUID id){ return  false;}
    public boolean buscarEspacioEnBD(){ return  false;}
}
