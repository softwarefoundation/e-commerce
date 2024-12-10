package br.com.devchampions.ecommerce.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class JwtService {

    @Autowired
    private JwtEncoder encoder;

    public String token(Authentication authentication) {

        Instant now = Instant.now();
        long expiry = 36000L;

        String scope = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors
                        .joining(" "));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("www.e-commerce.com.br")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject("Tom")
                .claim("scope", "ROLES")
                .build();

        return encoder.encode(
                        JwtEncoderParameters.from(claims))
                .getTokenValue();
    }

}

