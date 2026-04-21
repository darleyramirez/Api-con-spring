package com.example.PaseoApp.Servicios;


import com.example.PaseoApp.models.Usuario;
import com.example.PaseoApp.repositorios.UsuarioRepositorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioServicio {
    //Inyectandop una depndnecia al respositorio usuario

    @Autowired
    private  UsuarioRepositorios repositorioUsuario;


public Usuario guardarUsuarioEnBD(Usuario datos){
      //Conciones logicas para validar datos a guardar 
      // 1) validar que el correo a registrar nose haya guardado previamente 

    if(repositorioUsuario.findByCorreo(datos.getCorreo()).isPresent()){
        throw new ResponseStatusException(
            HttpStatus.CONFLICT,
            "Ya existe un correo registrado"
        );
    }

    if (datos.getNombres() == null || datos.getNombres().isBlank()) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "El nombre de usuario no puede enviarse vacío"
        );
    }

    if (datos.getContraseña() == null || datos.getContraseña().length() < 6) {
        throw new ResponseStatusException(
            HttpStatus.BAD_REQUEST,
            "La contraseña debe tener al menos 6 caracteres"
        );
    }

    return this.repositorioUsuario.save(datos);
}

        // Si paso la zona de validaciones proceso a preparar la receta en terminos tecnico (proceso a ejecutar la query que se necesita)

        // Validar que datos me envian y si estos cumplen las reglas del negocio
        // Guardar los datos en BD con ayuda del repositorio
       
    

       
    public Usuario modificarUsuarioEnBD(Usuario datos, UUID id){
        // Validar que tipos se envian y si estos cumplen las reglas de negocio
        // modificar los datos en Bd con ayuda del repositorio

        //1) Buscar si el usuario existe en base de datos 
        Optional<Usuario> usuario_que_estoy_Buscando = this.repositorioUsuario.findById(id);
        if(usuario_que_estoy_Buscando.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "El usuario que quieres buscar no Exite en BD"
            );
        }
        Usuario usuario_que_encontre = usuario_que_estoy_Buscando.get();

        //2 validar la informacion nueva que me mandan el cliente
        if(datos.getNombres().isEmpty() || datos.getNombres().isBlank()){
            throw new ResponseStatusException(
                HttpStatus.BAD_REQUEST,
                "Revisa el nombre del Ingresado"
            );
        }

        //3 ejecutar el nuevo guardado y retornar (Utilizo set para modificar y ponerselo a los get lo viejo)
        usuario_que_encontre.setNombres(datos.getNombres());
        return this.repositorioUsuario.save(usuario_que_encontre);         
    }

    public boolean eliminarUsuarioEnBD(UUID id){
        // Buscar y validar si el id que me envian existe
        // Si es asi eliminar el registro en la base de datos


        Optional<Usuario> usuario_que_estoy_Buscando = this.repositorioUsuario.findById(id);
        if(usuario_que_estoy_Buscando.isEmpty()){
            throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "El usuario que quieres Eliminar no Exite en BD"
            );
        }
        this.repositorioUsuario.deleteById(id);
        return true;
    }
 
    public List<Usuario> buscarUsuariosEnBD(){
        //Dependiendo del parametro de busqueda debo implementar validaciones
        //Devuelvo los usuarios o usuario que encuentre en BD
        List<Usuario> usuariosEncontrados = this.repositorioUsuario.findAll();
        
        return usuariosEncontrados;
    }
}
