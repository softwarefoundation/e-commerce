package br.com.devchampions.ecommerce.security.service;

import br.com.devchampions.ecommerce.security.entity.Authority;
import br.com.devchampions.ecommerce.security.entity.Login;
import br.com.devchampions.ecommerce.security.entity.dto.UsuarioDetailsDtoImpl;
import br.com.devchampions.ecommerce.security.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UsuarioDetailsDtoImpl loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("Username: {}", username);

        Login login = this.userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário ou senha inválido"));

        UsuarioDetailsDtoImpl usuarioDetailsDto = new UsuarioDetailsDtoImpl();
        usuarioDetailsDto.setUsername(login.getUsername());
        usuarioDetailsDto.setPassword(login.getPassword());

        List<GrantedAuthority> authorities = new ArrayList<>();
        for (Authority authority : login.getAuthorities()) {
            authorities.add(new SimpleGrantedAuthority(authority.getNome()));
        }
        usuarioDetailsDto.setAuthorities(authorities);

        return usuarioDetailsDto;
    }

}
