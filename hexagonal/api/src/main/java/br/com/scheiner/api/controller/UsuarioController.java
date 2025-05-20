package br.com.scheiner.api.controller;

import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.scheiner.api.dto.UsuarioRequestDTO;
import br.com.scheiner.api.mapper.UsuarioMapper;
import br.com.scheiner.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@Operation(summary = "Criar um novo usuário")
	public ResponseEntity<UsuarioRequestDTO> criarUsuario(@RequestBody @Valid UsuarioRequestDTO usuarioDto) {

		var usuarioCriado = usuarioService.criarUsuario(UsuarioMapper.INSTANCE.toDomain(usuarioDto));

		return ResponseEntity.ok(UsuarioMapper.INSTANCE.toDto(usuarioCriado));
	}

	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	@Operation(summary = "Buscar usuário por ID")
	public ResponseEntity<UsuarioRequestDTO> obterUsuarioPorId(@PathVariable UUID id) {
		
		var usuario = usuarioService.obterUsuarioPorId(id);
		
		return ResponseEntity.ok(UsuarioMapper.INSTANCE.toDto(usuario));
	}
}
