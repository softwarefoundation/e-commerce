package br.com.devchampions.ecommerce.security;

import br.com.devchampions.ecommerce.security.config.CorsConfigurationSourceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import static br.com.devchampions.ecommerce.security.ConstantesSecurity.DEFAULT_PREFIX_ESCOPE_;

@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {


    @Autowired
    CorsConfigurationSourceImpl configurationSource;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {

        configurarPermissoesDasRotas(httpSecurity);

        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .cors(c -> c.configurationSource(configurationSource))
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return httpSecurity.build();
    }

    private void configurarPermissoesDasRotas(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests(auth -> auth.requestMatchers(HttpMethod.POST, "/produto/**").hasAuthority(DEFAULT_PREFIX_ESCOPE_.concat("produto:cadastro")));
    }

}
