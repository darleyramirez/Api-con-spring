package com.example.PaseoApp.validadores;

import com.example.PaseoApp.models.Espacios;
import com.example.PaseoApp.repositorios.EspaciosRepositorios;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

@Component
public class ValidadorEspacioImpl implements ValidadorEspacio {

    private final EspaciosRepositorios espaciosRepositorios;

    public ValidadorEspacioImpl(EspaciosRepositorios espaciosRepositorios) {
        this.espaciosRepositorios = espaciosRepositorios;
    }

    @Override
    public void validarNuevoEspacio(Espacios datos) {
        validarEspacio(datos);
        validarNombreNoVacio(datos.getNombre());
        validarNombreNoDuplicado(datos.getNombre());
        validarDescripcionNoVacia(datos.getDescripcion());
        validarAforoValido(datos.getAforo());
    }

    @Override
    public void validarDatosModificacion(Espacios datos) {
        validarEspacio(datos);
        validarNombreNoVacio(datos.getNombre());
        validarDescripcionNoVacia(datos.getDescripcion());
        validarAforoValido(datos.getAforo());
    }

    private void validarEspacio(Espacios datos) {
        if (datos == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Los datos del espacio son obligatorios");
        }
    }

    private void validarNombreNoVacio(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El nombre del espacio no puede enviarse vacío");
        }
    }

    private void validarNombreNoDuplicado(String nombre) {
        if (nombre == null || nombre.isBlank()) {
            return;
        }
        if (espaciosRepositorios.findByNombre(nombre).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un espacio registrado con ese nombre");
        }
    }

    private void validarDescripcionNoVacia(String descripcion) {
        if (descripcion == null || descripcion.isBlank()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "La descripción del espacio no puede enviarse vacía");
        }
    }

    private void validarAforoValido(Integer aforo) {
        if (aforo == null || aforo <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "El aforo debe ser mayor a 0");
        }
    }
}
