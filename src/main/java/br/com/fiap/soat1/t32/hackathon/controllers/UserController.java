package br.com.fiap.soat1.t32.hackathon.controllers;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import br.com.fiap.soat1.t32.hackathon.models.user.User;
import br.com.fiap.soat1.t32.hackathon.services.UserService;

import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@Tag(name = "user", description = "API User")
@RequestMapping(value = "/v1/user",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;

	@Operation(description = "Consultar Usuario")
	@GetMapping(path = "/{userId}", 
		consumes = { ALL_VALUE }, 
		produces = { APPLICATION_JSON_VALUE, ALL_VALUE })
	@ApiResponse(responseCode = "200", description = "Usuario Consultado com sucesso")
    public ResponseEntity<User> consultarUsuer(@PathVariable String userId){

		return ResponseEntity.ok(userService.consultarUsuer(userId));
    }

	@Operation(description = "solicitar email com pontos do usuario")
	@PostMapping(path = "/{userId}/report/pontos", 
		consumes = { ALL_VALUE }, 
		produces = { APPLICATION_JSON_VALUE, ALL_VALUE })
	@ApiResponse(responseCode = "200", description = "Relatório pontos do usuario solicitado, será enviado no email.")
    public ResponseEntity<Void> solicitarRelatorioEmail(){
        return ResponseEntity.noContent().build();
    }

}
