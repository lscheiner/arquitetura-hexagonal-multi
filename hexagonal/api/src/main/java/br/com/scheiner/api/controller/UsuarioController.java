package br.com.scheiner.api.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.scheiner.api.dto.UsuarioRequestDTO;
import br.com.scheiner.api.dto.UsuarioResponseDTO;
import br.com.scheiner.api.mapper.UsuarioMapper;
import br.com.scheiner.core.annotation.ValidarIdentificador;
import br.com.scheiner.core.annotation.ValidarResponse;
import br.com.scheiner.domain.service.UsuarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/usuarios")
@RequiredArgsConstructor
@Tag(name = "Usuários", description = "Operações relacionadas a usuários")
public class UsuarioController {

	private final UsuarioService usuarioService;

	@ValidarResponse
	@ResponseStatus(code = HttpStatus.CREATED)
	@PostMapping
	@Operation(summary = "Criar um novo usuário")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso" , 
		    		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE , schema = @Schema(implementation = UsuarioResponseDTO.class))),
	})
	public ResponseEntity<UsuarioResponseDTO> criarUsuario(@RequestHeader HttpHeaders headers , @ValidarIdentificador("email") @RequestBody @Valid UsuarioRequestDTO usuarioDto) {

		var usuarioCriado = usuarioService.criarUsuario(UsuarioMapper.INSTANCE.toDomain(usuarioDto));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.INSTANCE.toDto(usuarioCriado));
	}

	@ValidarResponse
	@ResponseStatus(code = HttpStatus.OK)
	@GetMapping("/{id}")
	@Operation(summary = "Buscar usuário por ID")
	@ApiResponses(value = {
		    @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso" , 
		    		content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE , schema = @Schema(implementation = UsuarioResponseDTO.class))),
	})
	public ResponseEntity<UsuarioResponseDTO> obterUsuarioPorId(@ValidarIdentificador @PathVariable UUID id) {
		
		var usuario = usuarioService.obterUsuarioPorId(id);
		
		return ResponseEntity.status(HttpStatus.OK).body(UsuarioMapper.INSTANCE.toDto(usuario));
	}
}
