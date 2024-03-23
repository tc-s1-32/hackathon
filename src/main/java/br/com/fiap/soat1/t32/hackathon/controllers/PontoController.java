package br.com.fiap.soat1.t32.hackathon.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.fiap.soat1.t32.hackathon.enums.EventoPronto;
import br.com.fiap.soat1.t32.hackathon.models.user.presenters.ListaPontosResponse;
import br.com.fiap.soat1.t32.hackathon.services.PontoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import lombok.RequiredArgsConstructor;

import static org.springframework.http.MediaType.ALL_VALUE;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import java.time.LocalDateTime;

@Tag(name = "ponto", description = "API ponto")
@RequestMapping(value = "/v1/ponto",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
@RestController
@RequiredArgsConstructor
public class PontoController {

	private final PontoService pontoService;

    @PostMapping(path = "/{userId}",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
    @ApiResponse(responseCode = "201", description = "Cadastra proximo ponto e retorna pontos do dia")
    @Operation(description = "Cadastra pontos para o usuário informado")
    public ResponseEntity<ListaPontosResponse> cadastrarPonto(@PathVariable String userId){
        var pontos = pontoService.cadastrarPonto(userId);
        var listaPontosResponse = ListaPontosResponse.builder().pontos(pontos).build();

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(listaPontosResponse);
    }

    @PostMapping(path = "/saida/{userId}",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
    @ApiResponse(responseCode = "201", description = "Cadastra ponto do tipo saida e retorna pontos do dia")
    @Operation(description = "Cadastra ponto do tipo saida")
    public ResponseEntity<ListaPontosResponse>  cadastrarPontoSaida(@PathVariable String userId){
        var pontos = pontoService.cadastrarPonto(userId, EventoPronto.SAIDA);
        var listaPontosResponse = ListaPontosResponse.builder().pontos(pontos).build();

        return ResponseEntity.status(HttpStatus.CREATED)
            .body(listaPontosResponse);
    }

    @GetMapping(path = "/{userId}/day",
        consumes = {APPLICATION_JSON_VALUE, ALL_VALUE},
        produces = {APPLICATION_JSON_VALUE})
    @ApiResponse(responseCode = "200", description = "Retorna pontos do dia")
    @Operation(description = "Retorna pontos do dia")
    public ResponseEntity<ListaPontosResponse> consultarPontosDia(@PathVariable String userId){
        LocalDateTime dataInicial = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dataFinal = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        var pontos = pontoService.getfindPontosByUserIdAndData(userId, dataInicial, dataFinal);
        var listaPontosResponse = ListaPontosResponse.builder().pontos(pontos).build();
        
        return ResponseEntity.ok(listaPontosResponse);
    }
}