package com.example.PaseoApp.Servicios;

import com.example.PaseoApp.models.Espacios;
import com.example.PaseoApp.repositorios.EspaciosRepositorios;
import com.example.PaseoApp.validadores.ValidadorEspacio;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EspacioServicioImpl implements EspacioServicio {

    private final EspaciosRepositorios repositorioEspacio;
    private final ValidadorEspacio validadorEspacio;

    public EspacioServicioImpl(EspaciosRepositorios repositorioEspacio, ValidadorEspacio validadorEspacio) {
        this.repositorioEspacio = repositorioEspacio;
        this.validadorEspacio = validadorEspacio;
    }

    @Override
    public Espacios guardarEspacioEnBD(Espacios datos) {
        validadorEspacio.validarNuevoEspacio(datos);
        return this.repositorioEspacio.save(datos);
    }

    @Override
    public Espacios modificarEspacioEnBD(Espacios datos, UUID id) {
        if (datos == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Datos de espacio obligatorios");
        }

        Optional<Espacios> espacioBuscado = this.repositorioEspacio.findById(id);
        if (espacioBuscado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El espacio que quieres editar no existe en BD");
        }

        Espacios espacioExistente = espacioBuscado.get();

        validadorEspacio.validarDatosModificacion(datos);

        if (!datos.getNombre().equals(espacioExistente.getNombre()) && repositorioEspacio.findByNombre(datos.getNombre()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Ya existe un espacio registrado con ese nombre");
        }

        espacioExistente.setNombre(datos.getNombre());
        espacioExistente.setDescripcion(datos.getDescripcion());
        espacioExistente.setFoto(datos.getFoto());
        espacioExistente.setAforo(datos.getAforo());

        return this.repositorioEspacio.save(espacioExistente);
    }

    @Override
    public boolean eliminarEspacioEnBD(UUID id) {
        Optional<Espacios> espacioBuscado = this.repositorioEspacio.findById(id);
        if (espacioBuscado.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "El espacio que quieres eliminar no existe en BD");
        }

        this.repositorioEspacio.deleteById(id);
        return true;
    }

    @Override
    public List<Espacios> buscarEspaciosEnBD() {
        return this.repositorioEspacio.findAll();
    }
}
