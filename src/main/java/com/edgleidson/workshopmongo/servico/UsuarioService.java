package com.edgleidson.workshopmongo.servico;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgleidson.workshopmongo.dominio.Usuario;
import com.edgleidson.workshopmongo.repositorio.UsuarioRepository;

@Service
public class UsuarioService {

	// Injeção de dependência.
	@Autowired
	private UsuarioRepository repositorio;
	
	public List<Usuario> buscarTodos(){
		return repositorio.findAll();
	}
}
