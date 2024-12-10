package br.com.devchampions.ecommerce.security;

import br.com.devchampions.ecommerce.security.config.CorsConfigurationSourceImpl;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.jwk.OctetSequenceKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    CorsConfigurationSourceImpl configurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .cors(c -> c.configurationSource(configurationSource))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));


        return httpSecurity.build();
    }


//    @Bean
//    JwtDecoder jwtDecoder() {
//        log.info("JWT Decoder...");
//        OctetSequenceKey key = new OctetSequenceKey.Builder("123456".getBytes()).algorithm(JWSAlgorithm.parse("HS256")).build();
//        return NimbusJwtDecoder.withSecretKey(key.toSecretKey()).build();
//    }

}
