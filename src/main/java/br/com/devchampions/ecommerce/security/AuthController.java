package br.com.devchampions.ecommerce.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/auth")
public class AuthController {


    @PostMapping("/authenticate")
    public String authenticate(Authentication authentication){

        log.info("Auth: {}", authentication.getPrincipal().toString());

        return "OK";
    }

}
