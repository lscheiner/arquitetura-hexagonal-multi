package br.com.scheiner.api.dto;

import java.util.UUID;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UsuarioResponseDTO {

    @Schema(description = "ID usuário", example = "b74a9cd2-7240-4d1e-b93c-cf8d13c92ae5")
    private UUID id;
    
    @Schema(description = "Nome completo do usuário", example = "João da Silva")
    private String nome;

    @Schema(description = "Endereço de e-mail válido", example = "joao.silva@exemplo.com")
    private String email;
}
