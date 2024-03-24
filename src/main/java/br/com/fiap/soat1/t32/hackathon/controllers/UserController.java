package br.com.fiap.soat1.t32.hackathon.controllers;

import br.com.fiap.soat1.t32.hackathon.services.AuthUtilsService;
import br.com.fiap.soat1.t32.hackathon.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

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
	@GetMapping(
		consumes = { ALL_VALUE }, 
		produces = { APPLICATION_JSON_VALUE })
	@ApiResponse(responseCode = "200", description = "Usuario Consultado com sucesso")
    public ResponseEntity<Map<String, Object>> consultarUsarioLogado(@AuthenticationPrincipal OidcUser principal){
		return ResponseEntity.ok(principal.getClaims());
    }

	@Operation(description = "solicitar email com pontos do usuario")
	@PostMapping(path = "/report/pontos",
		consumes = { ALL_VALUE }, 
		produces = { APPLICATION_JSON_VALUE })
	@ApiResponse(responseCode = "200", description = "Relatório pontos do usuario solicitado, será enviado no email.")
    public ResponseEntity<Void> solicitarRelatorioEmail(@AuthenticationPrincipal OidcUser principal){
		userService.solicitarRelatorioEmail(AuthUtilsService.getUserId(principal));
        return ResponseEntity.noContent().build();
    }

}
