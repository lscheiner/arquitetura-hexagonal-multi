package br.com.scheiner.domain.service.impl;

import java.util.UUID;

import org.springframework.stereotype.Service;

import br.com.scheiner.domain.model.Usuario;
import br.com.scheiner.domain.service.UsuarioRepository;
import br.com.scheiner.domain.service.UsuarioService;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService{

    private final UsuarioRepository usuarioRepository;
	
	@Override
	public Usuario criarUsuario(Usuario usuario) {
		return null;
	}

	@Override
	public Usuario obterUsuarioPorId(UUID id) {
		return null;
	}

}
