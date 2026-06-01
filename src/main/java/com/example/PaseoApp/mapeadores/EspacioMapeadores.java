package com.example.PaseoApp.mapeadores;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.PaseoApp.dto.EspacioRecordDto;
import com.example.PaseoApp.models.Espacios;


@Mapper(componentModel = "Spring")
public interface EspacioMapeadores {
    EspacioMapeadores instancia= Mappers.getMapper(EspacioMapeadores.class);

    EspacioRecordDto convertir_modelo_dto(Espacios espacios);
    
    List<EspacioRecordDto> convertir_lista_de_modelo_lista_dto(List<Espacios>Lista);
    
} 