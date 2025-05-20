package br.com.scheiner.repository.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.scheiner.domain.model.Usuario;
import br.com.scheiner.repository.entity.UsuarioEntity;

@Mapper
public interface UsuarioEntityMapper {

    UsuarioEntityMapper INSTANCE = Mappers.getMapper(UsuarioEntityMapper.class);

    UsuarioEntity toEntity(Usuario usuario);

    Usuario toDomain(UsuarioEntity usuarioEntity);
}
