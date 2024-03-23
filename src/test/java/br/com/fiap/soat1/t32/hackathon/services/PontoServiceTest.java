package br.com.fiap.soat1.t32.hackathon.services;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import br.com.fiap.soat1.t32.hackathon.enums.EventoPronto;
import br.com.fiap.soat1.t32.hackathon.models.user.Ponto;
import br.com.fiap.soat1.t32.hackathon.repositories.UserRepository;

public class PontoServiceTest {

    @Test
    public void testProximoEventoPronto_Entrada_ReturnsIntervaloOn() {
        UserRepository userRepository = mock(UserRepository.class);
        String userId = "123";
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(Ponto.builder().eventoPronto(EventoPronto.ENTRADA).build());
        when(userRepository.findPontosByUserIdAndData(userId)).thenReturn(pontos);
        PontoService pontoService = new PontoService(userRepository);
        assertEquals(EventoPronto.INTERVALO_ON, pontoService.proximoEventoPronto(userId));
    }

    @Test
    public void testProximoEventoPronto_IntervaloOn_ReturnsIntervaloOff() {
        UserRepository userRepository = mock(UserRepository.class);
        String userId = "123";
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(Ponto.builder().eventoPronto(EventoPronto.INTERVALO_ON).build());
        when(userRepository.findPontosByUserIdAndData(userId)).thenReturn(pontos);
        PontoService pontoService = new PontoService(userRepository);
        assertEquals(EventoPronto.INTERVALO_OFF, pontoService.proximoEventoPronto(userId));
    }

    @Test
    public void testProximoEventoPronto_IntervaloOff_ReturnsIntervaloOn() {
        UserRepository userRepository = mock(UserRepository.class);
        String userId = "123";
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(Ponto.builder().eventoPronto(EventoPronto.INTERVALO_OFF).build());
        when(userRepository.findPontosByUserIdAndData(userId)).thenReturn(pontos);
        PontoService pontoService = new PontoService(userRepository);
        assertEquals(EventoPronto.INTERVALO_ON, pontoService.proximoEventoPronto(userId));
    }

    @Test
    public void testProximoEventoPronto_Saida_ReturnsEntrada() {
        UserRepository userRepository = mock(UserRepository.class);
        String userId = "123";
        List<Ponto> pontos = new ArrayList<>();
        pontos.add(Ponto.builder().eventoPronto(EventoPronto.SAIDA).build());
        when(userRepository.findPontosByUserIdAndData(userId)).thenReturn(pontos);
        PontoService pontoService = new PontoService(userRepository);
        assertEquals(EventoPronto.ENTRADA, pontoService.proximoEventoPronto(userId));
    }

    @Test
    public void testProximoEventoPronto_EmptyList_ReturnsEntrada() {
        UserRepository userRepository = mock(UserRepository.class);
        String userId = "123";
        List<Ponto> pontos = new ArrayList<>();
        when(userRepository.findPontosByUserIdAndData(userId)).thenReturn(pontos);
        PontoService pontoService = new PontoService(userRepository);
        assertEquals(EventoPronto.ENTRADA, pontoService.proximoEventoPronto(userId));
    }
}
