package com.example.PaseoApp.dto;

import java.time.LocalDateTime;
import com.example.PaseoApp.Enums.FranjaHoraria;

public record RerservaRecordDto(FranjaHoraria tiempo, LocalDateTime fecha) {}
