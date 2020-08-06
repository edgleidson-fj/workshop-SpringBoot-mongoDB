package com.edgleidson.workshopmongo.repositorio;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.edgleidson.workshopmongo.dominio.Post;

// Interface de Repositorio extend de MongoRepository.
// MongoRepository<T, ID> __ T= Classe Dominio / ID= Tipo do ID da Classe Dominio.

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
