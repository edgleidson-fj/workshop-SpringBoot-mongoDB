package com.edgleidson.workshopmongo.servico;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgleidson.workshopmongo.dominio.Usuario;
import com.edgleidson.workshopmongo.dto.UsuarioDTO;
import com.edgleidson.workshopmongo.repositorio.UsuarioRepository;
import com.edgleidson.workshopmongo.servico.excecao.ObjetoNaoEncontradoException;

@Service
public class UsuarioService {

	// Injeção de dependência.
	@Autowired
	private UsuarioRepository repositorio;

	public List<Usuario> buscarTodos() {
		return repositorio.findAll();
	}

	public Usuario buscarPorId(String id) {
		Optional<Usuario> obj = repositorio.findById(id);		
		// orElseThrow = Retorne objeto ou lance uma exceção.  
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
	}
	
	public Usuario inserir(Usuario obj) {
		return repositorio.insert(obj);
	}
	
	public void excluir(String id) {
		buscarPorId(id);
		repositorio.deleteById(id);
	}
	
	public Usuario atualizar(Usuario obj) {
		Usuario novoObj = buscarPorId(obj.getId());
		atualizarArquivo(novoObj, obj);
		return repositorio.save(novoObj);
	}
	
	private void atualizarArquivo(Usuario novoObj, Usuario obj) {
		novoObj.setNome(obj.getNome());
		novoObj.setEmail(obj.getEmail());
	}

	// DTO.
	public Usuario paraDTO(UsuarioDTO usuarioDTO) {
		return new Usuario(usuarioDTO.getId(), usuarioDTO.getNome(), usuarioDTO.getEmail());
	}
	
}
