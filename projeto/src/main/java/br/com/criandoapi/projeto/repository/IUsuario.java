package br.com.criandoapi.projeto.repository;

import br.com.criandoapi.projeto.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IUsuario extends JpaRepository<Usuario, Integer> {

}
