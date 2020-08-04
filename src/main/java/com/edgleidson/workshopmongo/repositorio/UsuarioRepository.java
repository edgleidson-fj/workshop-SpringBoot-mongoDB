package com.edgleidson.workshopmongo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.edgleidson.workshopmongo.dominio.Usuario;

// Interface de Repositorio extend de MongoRepository.
// MongoRepository<T, ID> __ T= Classe Dominio / ID= Tipo do ID da Classe Dominio.

@Repository
public interface UsuarioRepository extends MongoRepository<Usuario, String>{

}
