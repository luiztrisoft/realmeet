package br.com.sw2you.realmeet.utils;

import br.com.sw2you.realmeet.mapper.RoomMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

public final class MapperUtils {
    private MapperUtils(){}

    public static RoomMapper roomMapper(){
        return Mappers.getMapper(RoomMapper.class);
    }
}

/*
Mappers.getMapper: Retorna instancia de mapper. Diferente da aplicação que cuida da injeção da dependencia, os testes nao usam o contexto spring
 */