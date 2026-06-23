package com.example.PaseoApp.validadores;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.example.PaseoApp.models.Usuario;
import com.example.PaseoApp.repositorios.UsuarioRepositorios;

@Component
public class ValidadorUsuarioImpl implements ValidadorUsuario {

    private final UsuarioRepositorios usuarioRepositorios;

    public ValidadorUsuarioImpl(UsuarioRepositorios usuarioRepositorios) {
        this.usuarioRepositorios = usuarioRepositorios;
    }
    // incluir validaciones(proximamente y validar)
    // guardar los datos y convertir la respuesta en un dto 

    @Override
    public void validarNuevoUsuario(Usuario datos) {
        validarNombreNoVacio(datos.getNombres());
        validarCorreoNoRepetido(datos.getCorreo());
        validarLongitudContrasena(datos.getContraseña());
    }

    @Override
    public void validarDatosModificacion(Usuario datos) {
        validarNombreNoVacio(datos.getNombres());
        validarLongitudContrasena(datos.getContraseña());
    }

    private void validarCorreoNoRepetido(String correo) {
        if (correo == null || correo.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El correo es obligatorio");
        }

        if (usuarioRepositorios.findByCorreo(correo).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un usuario con el correo ingresado");
        }
    }

    private void validarNombreNoVacio(String nombres) {
        if (nombres == null || nombres.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre es obligatorio, ingresalo por favor");
        }
    }

    private void validarLongitudContrasena(String contrasena) {
        if (contrasena == null || contrasena.length() < 6) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La contraseña no debe ser menor a 6 caracteres");
        }
    }
}
