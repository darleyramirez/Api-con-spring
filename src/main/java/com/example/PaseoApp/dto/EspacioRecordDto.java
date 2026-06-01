package com.example.PaseoApp.dto;
import java.util.UUID;
import com.example.PaseoApp.Enums.Rol;

public record EspacioRecordDto(String nombre, String foto, Integer aforo, String descripcion) {
    
}
