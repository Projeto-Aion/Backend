package org.generation.aion.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.generation.aion.model.postagem;
import org.generation.aion.repository.postagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/postagens")
@CrossOrigin(origins="*", allowedHeaders = "*") //ATENÇÃO
public class postagemController {

	@Autowired
	private postagemRepository repository;
	
	@GetMapping
	public ResponseEntity<List<postagem>> GetAll(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<postagem> GetById(@PathVariable Long id){
		return repository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/titulo/{titulo}")	
	public ResponseEntity<List<postagem>> GetbyTitulo(@PathVariable String titulo){
		return ResponseEntity.ok(repository.findAllByTituloContainingIgnoreCase(titulo));
	}
	
	@GetMapping("/descricao/{descricao}")	
	public ResponseEntity<List<postagem>> GetbyDescricao(@PathVariable String descricao){
		return ResponseEntity.ok(repository.findAllByDescricaoContainingIgnoreCase(descricao));
	}
	
	@GetMapping("/local/{local}")	
	public ResponseEntity<List<postagem>> GetbyLocal(@PathVariable String local){
		return ResponseEntity.ok(repository.findAllByLocalContainingIgnoreCase(local));
	}
	
	@GetMapping("/data/{data}")	
	public ResponseEntity<List<postagem>> GetbyData (@PathVariable LocalDateTime data){
		return ResponseEntity.ok(repository.findAllByData(data));
	}
	
	@PostMapping
	public ResponseEntity<postagem> post (@RequestBody postagem postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<postagem> put (@RequestBody postagem postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable Long id){
		repository.deleteById(id);
	}
}
