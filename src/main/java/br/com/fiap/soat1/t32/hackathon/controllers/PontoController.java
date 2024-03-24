package br.com.fiap.soat1.t32.hackathon.controllers;

import br.com.fiap.soat1.t32.hackathon.enums.EventoPronto;
import br.com.fiap.soat1.t32.hackathon.models.user.presenters.ListaPontosResponse;
import br.com.fiap.soat1.t32.hackathon.services.AuthUtilsService;
import br.com.fiap.soat1.t32.hackathon.services.PontoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Tag(name = "ponto", description = "API ponto")
@RequestMapping(value = "/v1/ponto",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
public class PontoController {

	private final PontoService pontoService;

    @PostMapping(consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
    @ApiResponse(responseCode = "201", description = "Cadastra proximo ponto e retorna pontos do dia")
    @Operation(description = "Cadastra pontos para o usuário informado")
    public ResponseEntity<ListaPontosResponse> cadastrarPonto(@AuthenticationPrincipal OidcUser principal){
        var pontos = pontoService.cadastrarPonto(AuthUtilsService.getUserId(principal));
        var listaPontosResponse = ListaPontosResponse.builder().pontos(pontos).build();

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(listaPontosResponse);
    }

    @PostMapping(path = "/saida",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
    @ApiResponse(responseCode = "201", description = "Cadastra ponto do tipo saida e retorna pontos do dia")
    @Operation(description = "Cadastra ponto do tipo saida")
    public ResponseEntity<ListaPontosResponse>  cadastrarPontoSaida(@AuthenticationPrincipal OidcUser principal){
        var pontos = pontoService.cadastrarPonto(AuthUtilsService.getUserId(principal), EventoPronto.SAIDA);
        var listaPontosResponse = ListaPontosResponse.builder().pontos(pontos).build();

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(listaPontosResponse);
    }

    @GetMapping(path = "/day",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
    @ApiResponse(responseCode = "200", description = "Retorna pontos do dia")
    @Operation(description = "Retorna pontos do dia")
    public ResponseEntity<ListaPontosResponse> consultarPontosDia(@AuthenticationPrincipal OidcUser principal){
        LocalDateTime dataInicial = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dataFinal = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        var pontos = pontoService.getfindPontosByUserIdAndData(AuthUtilsService.getUserId(principal), dataInicial, dataFinal);
        var listaPontosResponse = ListaPontosResponse.builder().pontos(pontos).build();
        
        return ResponseEntity.ok(listaPontosResponse);
    }
}