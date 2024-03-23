package br.com.fiap.soat1.t32.hackathon.repositories;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

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

    @Query("{'_id': ?0, 'pontos.data': {$gte: ?1, $lt: ?2}}")
    List<Ponto> findPontosByUserIdAndData(String userId, LocalDateTime dataInicial, LocalDateTime dataFinal);

}
