package br.com.fiap.soat1.t32.hackathon.models.user;

import java.time.LocalDateTime;

import br.com.fiap.soat1.t32.hackathon.enums.EventoPronto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Ponto {

    private LocalDateTime data;
    private EventoPronto eventoPronto;

}
