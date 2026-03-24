package com.example.PaseoApp.Servicios;


import com.example.PaseoApp.models.Usuario;
import com.example.PaseoApp.repositorios.UsuarioRepositorios;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsuarioServicio {
    //Inyectandop una depndnecia al respositorio usuario

    @Autowired
    private  UsuarioRepositorios usuarioRepositorios;


    public boolean guardarUsuarioEnBD(Usuario datos){
        // Validar que datos me envian y si estos cumplen las reglas del negocio
        // Guardar los datos en BD con ayuda del repositorio
        return  false; }
    public boolean modificarUusarioEnBD(Usuario datos, UUID id){
        // Validar que tipos se envian y si estos cumplen las reglas de negocio
        // modificar los datos en Bd con ayuda del repositorio
        return  false; }
    public boolean eliminarenBD(Usuario datos, UUID id){
        // Buscar y validar si el id que me envian existe
        // Si es asi eliminar el registro en la base de datos
        return  false; }
    public boolean modificarUusarioEnBD(UUID id){
        //Dependiendo del parametro de busqueda debo bsucar validaciones
        // Devolver los usuarios que encuentre en la base de datos
        return  false; }
    public boolean buscarUsuarioEnBD(){
        
        return  false; }
}
