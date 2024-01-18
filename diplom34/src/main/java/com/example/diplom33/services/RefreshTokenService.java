package com.example.diplom33.services;

import com.example.diplom33.models.RefreshToken;
import com.example.diplom33.models.User;
import com.example.diplom33.repositories.RefreshTokenRepository;
import com.example.diplom33.repositories.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@AllArgsConstructor
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final UserRepository userRepository;

    @Transactional
    public RefreshToken createRefreshToken(String phone){
        RefreshToken refreshToken = RefreshToken.builder()
                .user(userRepository.findByPhone(phone).get())
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusMillis(1000000)).build();
        return  refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token){
        return refreshTokenRepository.findByToken(token);
    }

    @Transactional
    public RefreshToken verifyExpiration(RefreshToken refreshToken){
        if(refreshToken.getExpiryDate().compareTo(Instant.now())<0){
            refreshTokenRepository.delete(refreshToken);
//                throw new RuntimeException(refreshToken.getToken() + "Срок токена истек. Нужна повторная авторизация");
            return null;
        }
        return refreshToken;
    }
}
