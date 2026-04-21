package com.example.PaseoApp.repositorios;

import com.example.PaseoApp.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
@Repository
public interface UsuarioRepositorios extends JpaRepository<Usuario, UUID> {
    //Acción para validar si la información esta o no esta.
    Optional<Usuario> findByCorreo(String correo);
}
