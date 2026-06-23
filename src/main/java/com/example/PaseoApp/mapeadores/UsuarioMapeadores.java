package com.example.PaseoApp.mapeadores;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.example.PaseoApp.dto.UsuarioRecordDto;
import com.example.PaseoApp.models.Usuario;

@Mapper(componentModel = "spring")
public interface UsuarioMapeadores {

    UsuarioMapeadores Instancia= Mappers.getMapper(UsuarioMapeadores.class);

    UsuarioRecordDto convertir_modelo_dto(Usuario usuario);
    
    List<UsuarioRecordDto> convertir_lista_de_modelo_lista_dto(List<Usuario>Lista);
} 