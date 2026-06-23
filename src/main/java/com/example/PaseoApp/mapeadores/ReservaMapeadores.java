package com.example.PaseoApp.mapeadores;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.PaseoApp.dto.RerservaRecordDto;
import com.example.PaseoApp.models.Reserva;

@Mapper(componentModel = "spring")
public interface ReservaMapeadores {

    ReservaMapeadores instancia= Mappers.getMapper(ReservaMapeadores.class);

    RerservaRecordDto convertir_modelo_dto(Reserva reserva);
    
    List<RerservaRecordDto> convertir_lista_de_modelo_lista_dto(List<Reserva>Lista);

    
} 
