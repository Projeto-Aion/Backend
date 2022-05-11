package org.generation.aion.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.generation.aion.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long> {
	
	public List<Postagem> findAllByTituloContainingIgnoreCase (String titulo);
	public List<Postagem> findAllByDescricaoContainingIgnoreCase (String descricao);
	public List<Postagem> findAllByLocalContainingIgnoreCase (String local);
	public List<Postagem> findAllByData (LocalDateTime data);

}
