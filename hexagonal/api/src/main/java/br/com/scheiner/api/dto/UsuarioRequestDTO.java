package br.com.scheiner.api.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    @Schema(description = "Nome completo do usuário", example = "João da Silva")
    @NotBlank(message = "O nome é obrigatório")
    @Size(min = 2, max = 100, message = "O nome deve ter entre 2 e 100 caracteres")
    private String nome;

    @Schema(description = "Endereço de e-mail válido", example = "joao.silva@exemplo.com")
    @NotBlank(message = "O e-mail é obrigatório")
    @Email(message = "O e-mail deve ser válido")
    @Size(max = 120, message = "O e-mail deve ter no máximo 120 caracteres")
    private String email;
}
