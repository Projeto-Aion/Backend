package org.generation.aion.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.generation.aion.model.Postagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PostagemRepository extends JpaRepository <Postagem, Long> {
	
	public List<Postagem> findAllByTituloContainingIgnoreCase (@Param ("titulo") String titulo);
	public List<Postagem> findAllByDescricaoContainingIgnoreCase (@Param ("descricao") String descricao);
	public List<Postagem> findAllByLocalContainingIgnoreCase (@Param ("local") String local);
	public List<Postagem> findAllByData (@Param ("data") LocalDateTime data);

}
