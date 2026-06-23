package com.example.PaseoApp.repositorios;

import com.example.PaseoApp.models.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ReservaRepositorios extends JpaRepository<Reserva, UUID> {
    Optional<Reserva> findByFecha(LocalDateTime fecha);
}
 