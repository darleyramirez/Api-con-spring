package com.example.PaseoApp.validadores;

import com.example.PaseoApp.models.Espacios;

public interface ValidadorEspacio {
    void validarNuevoEspacio(Espacios datos);

    void validarDatosModificacion(Espacios datos);
}
