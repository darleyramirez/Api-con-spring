package com.example.PaseoApp.Servicios;


import com.example.PaseoApp.models.Usuario;
import com.example.PaseoApp.repositorios.UsuarioRepositorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ReservaServicio {

    //Inyectandop una depndnecia al respositorio usuario

    @Autowired
    private ReservaServicio reservaServicio;

    public boolean guardarReservaEnBD(ReservaServicio datos){ return  false;}
    public boolean modificarReservaEnBD(ReservaServicio datos, UUID id){ return  false;}
    public boolean eliminarenBD(ReservaServicio datos, UUID id){ return  false;}
    public boolean modificarReservaEnBD(UUID id){ return  false;}
    public boolean buscarReservaEnBD(){ return  false;}
}
