package com.example.PaseoApp.repositorios;

import com.example.PaseoApp.models.Espacios;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface EspaciosRepositorios extends JpaRepository<Espacios, UUID> {
}
