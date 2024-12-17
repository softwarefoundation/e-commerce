package br.com.devchampions.ecommerce.security.service;

import br.com.devchampions.ecommerce.security.AccessTokenResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

import static br.com.devchampions.ecommerce.security.ConstantesSecurity.DEFAULT_AUTHORITIES_CLAIM_DELIMITER;
import static br.com.devchampions.ecommerce.security.ConstantesSecurity.DEFAULT_AUTHORITIES_CLAIM_NAME;

@Service
public class JwtService {


    @Autowired
    private JwtEncoder encoder;

    public AccessTokenResponse token(Authentication authentication) {

        Instant now = Instant.now();
        long expiry = 36000L;

        String authorities = authentication
                .getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors
                        .joining(DEFAULT_AUTHORITIES_CLAIM_DELIMITER));

        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuer("www.e-commerce.com.br")
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiry))
                .subject(authentication.getName())
                .claim("authorities", authorities)
                .claim(DEFAULT_AUTHORITIES_CLAIM_NAME, authorities)
                .build();


        String jwt = encoder.encode(
                        JwtEncoderParameters.from(claims))
                .getTokenValue();

        AccessTokenResponse accessTokenResponse = new AccessTokenResponse();
        accessTokenResponse.setAccessToken(jwt);

        return accessTokenResponse;
    }

}

