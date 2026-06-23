package com.example.PaseoApp.Servicios;

import com.example.PaseoApp.models.Espacios;

import java.util.List;
import java.util.UUID;

public interface EspacioServicio {

    Espacios guardarEspacioEnBD(Espacios datos);

    Espacios modificarEspacioEnBD(Espacios datos, UUID id);

    boolean eliminarEspacioEnBD(UUID id);

    List<Espacios> buscarEspaciosEnBD();

}
