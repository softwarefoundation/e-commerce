package br.com.devchampions.ecommerce.security.config;

import br.com.devchampions.ecommerce.security.http.HttpAllowHeadersEnum;
import br.com.devchampions.ecommerce.security.http.HttpAllowedOriginsEnum;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.List;

@Component
public class CorsConfigurationSourceImpl implements CorsConfigurationSource {

    @Override
    public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
        CorsConfiguration corsConfiguration = new CorsConfiguration();

        corsConfiguration.setAllowedOrigins(List.of(HttpAllowedOriginsEnum.ECONMMERCE_FRONTEND.getUrl()));
        corsConfiguration.setAllowedMethods(List.of(HttpMethod.GET.name(), HttpMethod.POST.name(), HttpMethod.PUT.name(), HttpMethod.DELETE.name(), HttpMethod.OPTIONS.name()));
        corsConfiguration.setAllowedHeaders(List.of(HttpAllowHeadersEnum.CONTENT_TYPE.getNome(), HttpAllowHeadersEnum.AUTHORIZATION.getNome()));

        return corsConfiguration;
    }
}
