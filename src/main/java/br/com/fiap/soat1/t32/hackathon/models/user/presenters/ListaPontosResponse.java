package br.com.fiap.soat1.t32.hackathon.models.user.presenters;

import java.util.List;

import br.com.fiap.soat1.t32.hackathon.models.user.Ponto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ListaPontosResponse {
    	private List<Ponto> pontos;

}
