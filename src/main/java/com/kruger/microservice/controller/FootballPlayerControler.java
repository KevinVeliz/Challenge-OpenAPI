package com.kruger.microservice.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.kruger.microservice.model.ErrorResponse;
import com.kruger.microservice.model.FootBallPlayer;
import com.kruger.microservice.model.service.FootballPlayerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/footballplayer")
public class FootballPlayerControler {

	@Autowired
	private FootballPlayerService footballPlayerService;

	// -----------------------------------------------------------------------------------------------------------------------------------\\
	@Tag(name = "First Tag", description = "Data")
	@Operation(summary = "Data all players", description = "Data all players", tags = "First Tag")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(array = @ArraySchema(schema = @Schema(implementation = FootBallPlayer.class)))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Iterable<FootBallPlayer> findAll() {
		return footballPlayerService.findAll();
	}

	// -----------------------------------------------------------------------------------------------------------------------------------\\
	@Tag(name = "Second Tag", description = "Save players")
	@Operation(summary = "Crear un jugador", description = "Crear un jugador", tags = "Second Tag")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = FootBallPlayer.class))),
			@ApiResponse(responseCode = "409", description = "Player already exists", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@RequestMapping(value = "/savefootballplayer", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
	public FootBallPlayer save(@RequestBody FootBallPlayer entity) {
		return footballPlayerService.save(entity);
	}

	// -----------------------------------------------------------------------------------------------------------------------------------\\
	@Tag(name = "Third Tag", description = "Update players")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = FootBallPlayer.class))),
			@ApiResponse(responseCode = "404", description = "Player not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "400", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@Operation(summary = "Actualizar un jugador", description = "Actualizar a un jugador mediante su id", tags = "Third Tag")
	@RequestMapping(value = "/updatefootballplayer/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> edit(@PathVariable(value = "id") Integer id,  @RequestBody FootBallPlayer entity) {
		try {
			Optional<FootBallPlayer> opt = footballPlayerService.findById(entity.getId());
			if (opt.isPresent()) {
				return ResponseEntity.ok(footballPlayerService.save(entity));
			} else {
				return notFound();
			}
		} catch (Exception exp) {
			return badRequest(exp);
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------\\
	@Tag(name = "Fourth Tag", description = "Delete players")
	@Operation(summary = "Eliminar a un jugador", description = "Eliminar a un jugador por su id", tags = "Fourth Tag")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = FootBallPlayer.class))),
			@ApiResponse(responseCode = "505", description = "Bad request", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Player not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@RequestMapping(value = "/deletefootballplayer/{id}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> delete(@PathVariable(value = "id") Integer id) {

		try {
			Optional<FootBallPlayer> opt = footballPlayerService.findById(id);
			if (opt.isPresent()) {
				footballPlayerService.deleteById(id);
				return ResponseEntity.ok(opt.get());
			} else {
				return notFound();
			}
		} catch (Exception exp) {
			return badRequest(exp);
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------\\
	// ** BUSCAR JUGADOR */
	@Tag(name = "Fifth Tag", description = "Search players")
	@Operation(summary = "Buscar a un jugador", description = "Buscar a un jugador por su id", tags = "Fifth Tag")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "successful operation", content = @Content(schema = @Schema(implementation = FootBallPlayer.class))),
			@ApiResponse(responseCode = "400", description = "Bad request ", content = @Content(schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "404", description = "Player not found", content = @Content(schema = @Schema(implementation = ErrorResponse.class)))
	})
	@RequestMapping(value = "/searchfootballplayer/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		try {
			Optional<FootBallPlayer> opt = footballPlayerService.findById(id);
			if (opt.isPresent()) {
				return ResponseEntity.ok(opt.get());
			} else {
				return notFound();
			}
		} catch (Exception exp) {
			return badRequest(exp);
		}
	}

	// -----------------------------------------------------------------------------------------------------------------------------------\\
	private ResponseEntity<?> notFound() {
		return new ResponseEntity<>(
				new ErrorResponse(HttpStatus.NOT_FOUND.toString(), "Player not found"),
				HttpStatus.NOT_FOUND);
	}

	private ResponseEntity<?> badRequest(Throwable throwable) {
		return new ResponseEntity<>(
				new ErrorResponse(HttpStatus.BAD_REQUEST.toString(), "Bad request"),
				HttpStatus.BAD_REQUEST);
	}

	private ResponseEntity<?> conflict() {
		return new ResponseEntity<>(
				new ErrorResponse(HttpStatus.CONFLICT.toString(), "Player already exists"),
				HttpStatus.CONFLICT);
	}
}
