package com.example.PaseoApp.repositorios;

import com.example.PaseoApp.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ReservaRepositorios extends JpaRepository<Reserva, UUID> {
}
