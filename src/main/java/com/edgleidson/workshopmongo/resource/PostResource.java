package com.edgleidson.workshopmongo.resource;

import java.net.URLDecoder;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edgleidson.workshopmongo.dominio.Post;
import com.edgleidson.workshopmongo.resource.util.URL;
import com.edgleidson.workshopmongo.servico.PostService;

@RestController
@RequestMapping(value = "/posts") // Endpoint.
public class PostResource {

	// Injeção de dependência.
	@Autowired
	private PostService servico;

	// @PathVariable = Para associao ID que vem no @RequestMapping(GET).
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Post> buscarPorId(@PathVariable String id) {
		Post post = servico.buscarPorId(id);
		return ResponseEntity.ok().body(post);
	}

	// @RequestParam = Pegar paramentro da URL.
	@RequestMapping(value = "/titulopesquisa", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> buscarPorTitulo(@RequestParam(value = "texto", defaultValue = "") String texto) {
		texto = URL.decodificarParametro(texto);
		List<Post> lista = servico.buscarPorTitulo(texto);
		return ResponseEntity.ok().body(lista);
	}
}
