package br.com.scheiner.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.scheiner.repository.entity.UsuarioEntity;

public interface UsuarioJpaRepository  extends JpaRepository<UsuarioEntity, UUID> {

}
