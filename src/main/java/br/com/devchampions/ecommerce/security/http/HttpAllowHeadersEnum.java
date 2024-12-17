package br.com.devchampions.ecommerce.security.http;

public enum HttpAllowHeadersEnum {

    CONTENT_TYPE("content-type"),
    AUTHORIZATION("authorization");


    private String nome;

    HttpAllowHeadersEnum(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
}
