package com.edgleidson.workshopmongo.dto;

import java.io.Serializable;

import com.edgleidson.workshopmongo.dominio.Usuario;

/* DTO (Data Transfer Object): é um objeto que tem o papel de carregar dados 
 das entidades de forma simples, podendo inclusive "projetar" apenas alguns 
 dados da entidade original. */

public class UsuarioDTO implements Serializable{
	private static final long serialVersionUID = 1L;

	private String id;
	private String nome;
	private String email;
	
	public UsuarioDTO() {
		}
	
	public UsuarioDTO(Usuario obj) {
		id = obj.getId();
		nome = obj.getNome();
		email = obj.getEmail();
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}
