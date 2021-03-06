package com.edgleidson.workshopmongo.servico;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edgleidson.workshopmongo.dominio.Post;
import com.edgleidson.workshopmongo.repositorio.PostRepository;
import com.edgleidson.workshopmongo.servico.excecao.ObjetoNaoEncontradoException;

@Service
public class PostService {

	// Injeção de dependência.
	@Autowired
	private PostRepository repositorio;

	public Post buscarPorId(String id) {
		Optional<Post> obj = repositorio.findById(id);
		// orElseThrow = Retorne objeto ou lance uma exceção.
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));
	}

	public List<Post> buscarPorTitulo(String titulo) {
		// Containing = Contém
		// IgnoreCase = Ignorar o tamanho da letra.
//	  return repositorio.findByTituloContainingIgnoreCase(titulo);

		return repositorio.pesquisarTitulo(titulo);
	}

	public List<Post> pesquisaComVariosCriterios(String titulo, Date dataMinima, Date dataMaxima) { 
		// Calculo para considerar o dia todo até as 23:59 dataMaxima.
		dataMaxima = new Date(dataMaxima.getTime() + 24 * 60 * 60 * 1000);
		return repositorio.pesquisaComVariosCriterios(titulo, dataMinima, dataMaxima);
	}

	/*
	 * public List<Post> fullSearch(String text, Date minDate, Date maxDate) {
	 * maxDate = new Date(maxDate.getTime() + 24 * 60 * 60 * 1000); return
	 * repositorio.fullSearch(text, minDate, maxDate); }
	 */
}
