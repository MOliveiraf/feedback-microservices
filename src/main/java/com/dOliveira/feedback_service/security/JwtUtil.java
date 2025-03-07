package com.dOliveira.feedback_service.security;

import com.dOliveira.feedback_service.entity.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    // ✅ Chave secreta segura (mínimo 256 bits)
    private static final String SECRET_KEY = "3YVhd7b+FGG9iP1Fw83phDTeUpaEgNUn2HZxVdoH1zQ=";

    // ✅ Tempo de expiração do token (1 dia)
    private static final long EXPIRATION_TIME = 86400000;

    // ✅ Método correto para gerar a chave de assinatura
    private static Key getSigningKey() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes); // Gera uma chave compatível com HMAC-SHA 256
    }

    // ✅ Método atualizado para gerar o token JWT
    public String generateToken(User user) {
        return Jwts.builder()
                .subject(user.getEmail()) // Define o e-mail como subject do token
                .claim("role", user.getRole().name()) // Adiciona a role do usuário como claim
                .issuedAt(new Date()) // Data de emissão do token
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)) // Define o tempo de expiração
                .signWith(getSigningKey()) // 📌 CORREÇÃO: Agora passando um `Key`, não um `String`
                .compact();
    }

    // ✅ Método corrigido para extrair os claims do token usando JJWT 0.12.6
    public Claims extractClaims(String token) {
        return Jwts.parser() // 📌 CORREÇÃO: Usar `parser()`
                .setSigningKey(getSigningKey()) // 📌 CORREÇÃO: `setSigningKey()` ao invés de `verifyWith()`
                .build()
                .parseSignedClaims(token) // 📌 Usando `parseSignedClaims()` corretamente
                .getPayload(); // Obtém o corpo do token
    }

    // ✅ Método para extrair o email do usuário do token JWT
    public String extractEmail(String token) {
        return extractClaims(token).getSubject(); // Obtém o "subject" (e-mail)
    }

    // ✅ Método para validar se um token é válido
    public boolean isTokenValid(String token) {
        try {
            extractClaims(token); // Se conseguir extrair os claims, o token é válido
            return true;
        } catch (Exception e) {
            return false; // Se houver erro ao decodificar, o token é inválido
        }
    }
}
