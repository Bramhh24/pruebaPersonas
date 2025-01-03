package com.bramh.pruebaPersonas.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.bramh.pruebaPersonas.Models.Persona;

@Repository
public interface PersonaRepository extends ReactiveMongoRepository<Persona, String> {

}
