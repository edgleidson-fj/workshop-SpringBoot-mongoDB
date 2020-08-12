package com.edgleidson.workshopmongo.repositorio;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.edgleidson.workshopmongo.dominio.Post;

// Interface de Repositorio extend de MongoRepository.
// MongoRepository<T, ID> __ T= Classe Dominio / ID= Tipo do ID da Classe Dominio.

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	// Query Methods.
	List<Post> findByTituloContainingIgnoreCase(String titulo);
	

	// @Query = Executar consulta personalizada com string Json específica do MongoDB.
	// $regex = Contém informação com a String
	// ?0 = Primeiro parâmetro. Ex: titulo.
	// 'i' = Case Sentive / Letras maiuscula e minuscula.
	@Query("{'titulo': { $regex: ?0 , $options: 'i'} }")
	List<Post> pesquisarTitulo(String titulo);
	
	
	
	/*/ $gte = Maior ou igual.
	// $ite = Menor ou igual.
	// ?1 = Segundo parâmetro. Ex: dataMinima.
	// ?2 = Terceira parâmetro. Ex: dataMaxima.
	@Query("{ $and: [ {data: {$gte: ?1}, {data: {$ite: ?2}, { "
			+ "$or: [{'titulo': { $regex: ?0 , $options: 'i'} }, "
			+ "{'corpo': { $regex: ?0 , $options: 'i'} }, "
			+ "{'comentarios.texto': { $regex: ?0 , $options: 'i'} } ]}  ]}")
	List<Post> pesquisaComVariosCriterios(String titulo, Date dataMinima, Date dataMaxima);*/
	
	

	
	
	@Query("{ $and: [ { data: {$gte: ?1} }, { data: { $lte: ?2} } , { $or: [ { 'titulo': { $regex: ?0, $options: 'i' } }, { 'corpo': { $regex: ?0, $options: 'i' } }, { 'comentarios.texto': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> pesquisaComVariosCriterios(String text, Date minDate, Date maxDate);
}
 