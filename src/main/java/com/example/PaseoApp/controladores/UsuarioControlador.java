package com.example.PaseoApp.controladores;

import com.example.PaseoApp.dto.UsuarioRecordDto;
import com.example.PaseoApp.mapeadores.UsuarioMapeadores;
import com.example.PaseoApp.models.Usuario;
import com.example.PaseoApp.Servicios.UsuarioServicio;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/paseoapi/v1/usuarios")
public class UsuarioControlador {

    //Por cada serivicio ofrecido
    //configuro 1 funcion controladora

    private final UsuarioServicio servicio;
    private final UsuarioMapeadores usuarioMapeadores;

    public UsuarioControlador(UsuarioServicio servicio, UsuarioMapeadores usuarioMapeadores) {
        this.servicio = servicio;
        this.usuarioMapeadores = usuarioMapeadores;
    }
    //funcion para controlar el guardado
    @PostMapping
    public ResponseEntity<UsuarioRecordDto> controlarGuardado(@RequestBody Usuario datos){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(usuarioMapeadores.convertir_modelo_dto(this.servicio.guardarUsuarioEnBD(datos)));
    }
    //funcion para controlar las modificaciones
    @PutMapping("/{id}")
    public ResponseEntity<UsuarioRecordDto> controlarModificado(@RequestBody Usuario datos, @PathVariable UUID id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioMapeadores.convertir_modelo_dto(this.servicio.modificarUsuarioEnBD(datos,id)));
    }

    //funcion para controlar el borrado
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> controlarBorrado(@PathVariable UUID id){
        this.servicio.eliminarUsuarioEnBD(id);
        return ResponseEntity.noContent().build();
    }


    //funcion para controlar el listar
    @GetMapping
    public ResponseEntity<List<UsuarioRecordDto>> controlarListado(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(usuarioMapeadores.convertir_lista_de_modelo_lista_dto(this.servicio.buscarUsuariosEnBD()));
    }

}