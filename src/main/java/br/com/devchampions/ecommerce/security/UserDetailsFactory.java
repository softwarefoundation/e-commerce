package br.com.devchampions.ecommerce.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;

public class UserDetailsFactory {


    /**
     * admin:admin
     *
     * @return UserDetails
     */
    public static UserDetails criarUsuarioSenhaTextoPlano() {
        return criarUsuario("admin", "{noop}admin");
    }

    /**
     * admin:admin
     *
     * @return UserDetails
     */
    public static UserDetails criarUsuarioSenhaBCryptPasswordEncoder() {
        return criarUsuario("admin", "$2a$10$lCl75SOeP0s5oPnqb2.M8.UpCxkf.JJUWPVdHYtdapElpG/H0ABae");
    }


    private static UserDetails criarUsuario(String username, String password) {
        return new UserDetails() {

            @Override
            public String getUsername() {
                return username;
            }

            @Override
            public String getPassword() {
                return password;
            }

            @Override
            public Collection<? extends GrantedAuthority> getAuthorities() {
                return new ArrayList<>();
            }
        };
    }

}
