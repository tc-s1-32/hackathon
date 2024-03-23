package br.com.fiap.soat1.t32.hackathon.services;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.hackathon.models.user.presenters.RelatorioPontoRequest;

@Slf4j
@Service
@RequiredArgsConstructor
public class PontoEventListenerService {

    private final RabbitTemplate rabbitTemplate;

    @Value("${fila.relatorio.ponto}")
	private String filaRelatorioPonto;

	public void postRelatorioPonto(RelatorioPontoRequest relatorioPontoRequest) {
		try{
			rabbitTemplate.convertAndSend(filaRelatorioPonto, relatorioPontoRequest);
		}catch (Exception ex){
			log.error("Falha ao criar pedido", ex);
		}
	}

}