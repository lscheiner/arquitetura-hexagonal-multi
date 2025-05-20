package br.com.scheiner.domain.model;

import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Usuario {

	private UUID id;
	private String nome;
	private String email;
}
