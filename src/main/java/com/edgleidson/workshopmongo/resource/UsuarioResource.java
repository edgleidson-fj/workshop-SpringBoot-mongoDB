package com.edgleidson.workshopmongo.resource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edgleidson.workshopmongo.dominio.Usuario;


@RestController
@RequestMapping(value = "/usuarios") // EndPoint.
public class UsuarioResource {

	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Usuario>> buscarTodos() {
		List<Usuario> list = new ArrayList<>();
		Usuario u1 = new Usuario("1001", "Maria Brown", "maria@gmail.com");
		Usuario u2 = new Usuario("1002", "Alex Green", "alex@gmail.com");
		list.addAll(Arrays.asList(u1, u2));
		return ResponseEntity.ok().body(list);
	}

}
