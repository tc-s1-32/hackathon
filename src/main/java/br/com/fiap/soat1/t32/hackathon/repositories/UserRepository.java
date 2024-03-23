package br.com.fiap.soat1.t32.hackathon.repositories;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.mongodb.repository.MongoRepository;

import br.com.fiap.soat1.t32.hackathon.models.user.Ponto;
import br.com.fiap.soat1.t32.hackathon.models.user.User;


public interface UserRepository extends MongoRepository<User, String> {

    default User addPonto(String userId, Ponto ponto) {
        User user = findById(userId).orElse(null);
        if (user != null) {
            user.getPontos().add(ponto);
            return save(user);
        }
        return null;
    }

    default List<Ponto> findPontosByUserIdAndData(String userId){
        LocalDateTime dataInicial = LocalDateTime.now().withHour(0).withMinute(0).withSecond(0);
        LocalDateTime dataFinal = LocalDateTime.now().withHour(23).withMinute(59).withSecond(59);
        return findPontosByUserIdAndData(userId, dataInicial, dataFinal);
    }

    default List<Ponto> findPontosByUserIdAndData(String userId, LocalDateTime dataInicial, LocalDateTime dataFinal){
        User user = findById(userId).orElse(null);
        if (user != null) {
            return user.getPontos().stream()
                    .filter(ponto -> ponto.getData().isAfter(dataInicial) && ponto.getData().isBefore(dataFinal))
                    .collect(Collectors.toList());
        }
        return Collections.emptyList(); 
    }

}
