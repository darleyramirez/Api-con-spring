package com.example.PaseoApp.repositorios;

import com.example.PaseoApp.models.Espacios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;
import java.util.List;

@Repository
public interface EspaciosRepositorios extends JpaRepository<Espacios, UUID> {
    Optional<Espacios> findByNombre(String nombre);
}
