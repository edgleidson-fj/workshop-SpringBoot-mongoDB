package com.edgleidson.workshopmongo.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.edgleidson.workshopmongo.dominio.Usuario;
import com.edgleidson.workshopmongo.repositorio.UsuarioRepository;

// Implements CommandLineRunner.

@Configuration
public class Instanciacao implements CommandLineRunner {
	
	// Injeção de dependência.
	@Autowired
	private UsuarioRepository usuarioRepository;

	// Executar método na inicialização da aplicação.
	@Override
	public void run(String... args) throws Exception {
		usuarioRepository.deleteAll();
		
		Usuario maria = new Usuario(null, "Maria Brown", "maria@gmail.com");
		Usuario alex = new Usuario(null, "Alex Green", "alex@gmail.com");
		Usuario bob = new Usuario(null, "Bob Grey", "bob@gmail.com");
		
		usuarioRepository.saveAll(Arrays.asList(maria, alex, bob));
	}

}
