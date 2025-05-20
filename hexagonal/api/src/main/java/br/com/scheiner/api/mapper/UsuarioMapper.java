package br.com.scheiner.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.scheiner.api.dto.UsuarioRequestDTO;
import br.com.scheiner.domain.model.Usuario;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    UsuarioRequestDTO toDto(Usuario usuario);

    Usuario toDomain(UsuarioRequestDTO usuarioDTO);
}
