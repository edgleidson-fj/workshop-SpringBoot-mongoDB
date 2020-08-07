package com.edgleidson.workshopmongo.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edgleidson.workshopmongo.dominio.Post;
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
	}
