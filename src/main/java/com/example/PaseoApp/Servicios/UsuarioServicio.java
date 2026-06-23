package com.example.PaseoApp.Servicios;

import com.example.PaseoApp.models.Usuario;

import java.util.List;
import java.util.UUID;

public interface UsuarioServicio {

    Usuario guardarUsuarioEnBD(Usuario datos);

    Usuario modificarUsuarioEnBD(Usuario datos, UUID id);

    boolean eliminarUsuarioEnBD(UUID id);

    List<Usuario> buscarUsuariosEnBD();

}

