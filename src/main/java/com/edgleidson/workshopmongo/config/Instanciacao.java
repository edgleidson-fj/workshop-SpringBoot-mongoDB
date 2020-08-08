package com.edgleidson.workshopmongo.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.edgleidson.workshopmongo.dominio.Post;
import com.edgleidson.workshopmongo.dominio.Usuario;
import com.edgleidson.workshopmongo.dto.AutorDTO;
import com.edgleidson.workshopmongo.dto.ComentarioDTO;
import com.edgleidson.workshopmongo.repositorio.PostRepository;
import com.edgleidson.workshopmongo.repositorio.UsuarioRepository;

// Implements CommandLineRunner.

@Configuration
public class Instanciacao implements CommandLineRunner {
	
	// Injeção de dependência.
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PostRepository postRepository;

	// Executar método na inicialização da aplicação.
	@Override
	public void run(String... args) throws Exception {
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		usuarioRepository.deleteAll();
		postRepository.deleteAll();
		
		Usuario maria = new Usuario(null, "Maria Brown", "maria@gmail.com");
		Usuario alex = new Usuario(null, "Alex Green", "alex@gmail.com");
		Usuario bob = new Usuario(null, "Bob Grey", "bob@gmail.com");
		usuarioRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem!", "Vou viajar para São Paulo.", new AutorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia!", "Acordei feliz hoje.", new AutorDTO(maria));
		
		ComentarioDTO c1 = new ComentarioDTO("Boa viagem", sdf.parse("21/03/2018"), new AutorDTO(alex));
		ComentarioDTO c2 = new ComentarioDTO("Legal", sdf.parse("25/03/2018"), new AutorDTO(bob));
		ComentarioDTO c3 = new ComentarioDTO("Fé em Deus", sdf.parse("30/04/2018"), new AutorDTO(alex));
		
		post1.getComentarios().addAll(Arrays.asList(c1, c2));
		post2.getComentarios().addAll(Arrays.asList(c3));		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		usuarioRepository.save(maria);
	}

}
