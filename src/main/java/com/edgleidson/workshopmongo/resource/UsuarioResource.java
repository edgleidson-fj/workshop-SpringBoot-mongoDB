package com.edgleidson.workshopmongo.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.edgleidson.workshopmongo.dominio.Post;
import com.edgleidson.workshopmongo.dominio.Usuario;
import com.edgleidson.workshopmongo.dto.UsuarioDTO;
import com.edgleidson.workshopmongo.servico.UsuarioService;

@RestController
@RequestMapping(value = "/usuarios") // Endpoint.
public class UsuarioResource {

	// Injeção de dependência.
	@Autowired
	private UsuarioService servico;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> buscarTodos() {
		List<Usuario> lista = servico.buscarTodos();
		// Converter Lista em listaDTO. _ Expressão Lambda (x -> código).
		List<UsuarioDTO> listaDTO = lista.stream().map(x -> new UsuarioDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDTO);
	}

	// @PathVariable = Para associao ID que vem no @RequestMapping(GET).
	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<UsuarioDTO> buscarPorId(@PathVariable String id) {
		Usuario usuario = servico.buscarPorId(id);
		return ResponseEntity.ok().body(new UsuarioDTO(usuario));
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserir(@RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = servico.paraDTO(usuarioDTO);
		usuario = servico.inserir(usuario);		
		// Retornar o endereço do novo objeto inserido.
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> excluir(@PathVariable String id) {
		servico.excluir(id);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizar(@RequestBody UsuarioDTO usuarioDTO, @PathVariable String id) {
		Usuario usuario = servico.paraDTO(usuarioDTO);
		usuario.setId(id);		
		usuario = servico.atualizar(usuario);	
		return ResponseEntity.noContent().build();
		}
	
	@RequestMapping(value = "/{id}/posts", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> buscarPosts(@PathVariable String id) {
		Usuario usuario = servico.buscarPorId(id);
		return ResponseEntity.ok().body(usuario.getPosts());
	}
}
