package com.example.PaseoApp.validadores;

import com.example.PaseoApp.models.Usuario;

public interface ValidadorUsuario {
    void validarNuevoUsuario(Usuario datos);

    void validarDatosModificacion(Usuario datos);
}
