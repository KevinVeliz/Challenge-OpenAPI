package com.kruger.microservice.model.repository;

import org.springframework.data.repository.CrudRepository;

import com.kruger.microservice.model.FootBallPlayer;

public interface FootballPlayerRepository extends CrudRepository<FootBallPlayer, Integer>{
	
	//metodos genericos trabajan a partir de la persistencia y utilizamos para el CRUD
	
	
	
}
