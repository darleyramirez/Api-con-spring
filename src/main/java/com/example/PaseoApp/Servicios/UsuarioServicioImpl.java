package com.example.PaseoApp.Servicios;

import com.example.PaseoApp.models.Usuario;
import com.example.PaseoApp.repositorios.UsuarioRepositorios;
import com.example.PaseoApp.validadores.ValidadorUsuario;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

    private final UsuarioRepositorios repositorioUsuario;

    private final ValidadorUsuario validadorUsuario;

    public UsuarioServicioImpl(UsuarioRepositorios repositorioUsuario, ValidadorUsuario validadorUsuario) {
        this.repositorioUsuario = repositorioUsuario;
        this.validadorUsuario = validadorUsuario;
    }

    @Override
    public Usuario guardarUsuarioEnBD(Usuario datos) {
        if (datos == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de usuario obligatorios");
        }

        validadorUsuario.validarNuevoUsuario(datos);
        return this.repositorioUsuario.save(datos);
    }

    @Override
    public Usuario modificarUsuarioEnBD(Usuario datos, UUID id) {
        if (datos == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de usuario obligatorios");
        }

        validadorUsuario.validarDatosModificacion(datos);

        Optional<Usuario> usuarioExistente = this.repositorioUsuario.findById(id);
        if (usuarioExistente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario que quieres editar no existe en BD");
        }

        Usuario usuario = usuarioExistente.get();

        if (datos.getCorreo() == null || datos.getCorreo().isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo es obligatorio");
        }

        if (!datos.getCorreo().equals(usuario.getCorreo()) && repositorioUsuario.findByCorreo(datos.getCorreo()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un correo registrado con ese correo");
        }

        usuario.setNombres(datos.getNombres());
        usuario.setCorreo(datos.getCorreo());
        usuario.setContraseña(datos.getContraseña());
        usuario.setRol(datos.getRol());

        return this.repositorioUsuario.save(usuario);
    }

    @Override
    public boolean eliminarUsuarioEnBD(UUID id) {
        Optional<Usuario> usuarioExistente = this.repositorioUsuario.findById(id);
        if (usuarioExistente.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El usuario que quieres eliminar no existe en BD");
        }

        this.repositorioUsuario.deleteById(id);
        return true;
    }

    @Override
    public List<Usuario> buscarUsuariosEnBD() {
        return this.repositorioUsuario.findAll();
    }
}
