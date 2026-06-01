package com.example.PaseoApp.dto;

import java.util.UUID;
import com.example.PaseoApp.Enums.Rol;

public record UsuarioRecordDto(UUID id, String nombres, String correo, Rol rol) {
}
