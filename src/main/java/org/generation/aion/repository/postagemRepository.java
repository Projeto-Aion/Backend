package org.generation.aion.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.generation.aion.model.postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface postagemRepository extends JpaRepository <postagem, Long> {
	
	public List<postagem> findAllByTituloContainingIgnoreCase (String titulo);
	public List<postagem> findAllByDescricaoContainingIgnoreCase (String descricao);
	public List<postagem> findAllByLocalContainingIgnoreCase (String local);
	public List<postagem> findAllByData (LocalDateTime data);

}
