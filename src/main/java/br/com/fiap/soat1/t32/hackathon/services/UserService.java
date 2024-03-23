package br.com.fiap.soat1.t32.hackathon.services;


import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.fiap.soat1.t32.hackathon.models.user.User;
import br.com.fiap.soat1.t32.hackathon.repositories.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
    
    private final UserRepository userRepository;

    public User consultarUsuer(String userId){
        Optional<User> userOptional = userRepository.findById(userId);

        return userOptional.orElse(null);
    }

    
}
