package br.com.scheiner.domain.model;

import java.util.UUID;

import lombok.Getter;

@Getter
public class Usuario {

	private UUID id;
	private String nome;
	private String email;
}
