package br.com.fiap.soat1.t32.hackathon.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.hackathon.enums.EventoPronto;
import br.com.fiap.soat1.t32.hackathon.models.user.Ponto;
import br.com.fiap.soat1.t32.hackathon.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PontoService {

    private final UserRepository userRepository;

    public List<Ponto> getfindPontosByUserIdAndData(String userId, LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return userRepository.findPontosByUserIdAndData(userId, dataInicial, dataFinal);   
    }

    public List<Ponto> cadastrarPonto(String userId) {
        return cadastrarPonto(userId, null);
    }

    public List<Ponto> cadastrarPonto(String userId, EventoPronto eventoPronto) {
        Ponto ponto = new Ponto();
        ponto.setData(LocalDateTime.now());

        ponto.setEventoPronto((eventoPronto != EventoPronto.SAIDA) 
            ? proximoEventoPronto(userId)
            : eventoPronto);

        userRepository.addPonto(userId, ponto);
        
        return userRepository.findPontosByUserIdAndData(userId);
    }
    
    public EventoPronto proximoEventoPronto(String userId) {

        List<Ponto> pontos = userRepository.findPontosByUserIdAndData(userId);

        if (pontos.isEmpty()) {
            return EventoPronto.ENTRADA;
        }

        Ponto ultimoPonto = pontos.get(pontos.size() - 1);
        switch (ultimoPonto.getEventoPronto()) {
            case ENTRADA:
                return EventoPronto.INTERVALO_ON;
            case INTERVALO_ON:
                return EventoPronto.INTERVALO_OFF;
            case INTERVALO_OFF:
                return EventoPronto.INTERVALO_ON;
            case SAIDA:
                return EventoPronto.ENTRADA;
            default:
                return EventoPronto.ENTRADA; 
        }
    }

}
