package org.generation.aion.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
@Table(name="tb_usuarios")
public class Usuario {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nome;

	@Email
	private String usuario;

	private String nickname;

	private String foto;

	@NotNull
	@Size(min=8, max=255)
	private String senha;
	
	@Size(max=14)
	private String telefone;
	
	@OneToMany(mappedBy="usuario", cascade=CascadeType.ALL)
	@JsonIgnoreProperties("usuario")
	private List<postagem>postagem;

}
