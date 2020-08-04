package com.edgleidson.workshopmongo.resource;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edgleidson.workshopmongo.dominio.Usuario;
import com.edgleidson.workshopmongo.servico.UsuarioService;


@RestController
@RequestMapping(value = "/usuarios") // EndPoint.
public class UsuarioResource {

	// Injeção de dependência.
	@Autowired
	private UsuarioService servico;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> buscarTodos() {
		List<Usuario> lista =  servico.buscarTodos();		
		return ResponseEntity.ok().body(lista);
	}

}
