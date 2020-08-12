package com.edgleidson.workshopmongo.resource;

import java.util.Date;
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

	@RequestMapping(value = "/pesquisacompleta", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> pesquisaComVariosCriterios(
			@RequestParam(value = "texto", defaultValue = "") String texto,
			@RequestParam(value = "dataMinima", defaultValue = "") String dataMinima,
			@RequestParam(value = "dataMaxima", defaultValue = "") String dataMaxima) {
		System.out.println("2");
		texto = URL.decodificarParametro(texto);
		Date dataMin = URL.converterData(dataMinima, new Date(0L));
		Date dataMax = URL.converterData(dataMaxima, new Date());
		List<Post> lista = servico.pesquisaComVariosCriterios(texto, dataMin, dataMax);
		return ResponseEntity.ok().body(lista);
	}

	
	
	/*@RequestMapping(value = "/fullsearch", method = RequestMethod.GET)
	public ResponseEntity<List<Post>> fullSearch(
			@RequestParam(value = "text", defaultValue = "") String text,
			@RequestParam(value = "minDate", defaultValue = "") String minDate,
			@RequestParam(value = "maxDate", defaultValue = "") String maxDate) {
		text = URL.decodificarParametro(text);
		Date min = URL.converterData(minDate, new Date(0L));	
		Date max = URL.converterData(maxDate, new Date());
		List<Post> list = servico.fullSearch(text, min, max);
		return ResponseEntity.ok().body(list);
	}*/
}
