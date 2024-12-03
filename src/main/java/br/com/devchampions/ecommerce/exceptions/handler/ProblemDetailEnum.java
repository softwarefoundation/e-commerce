package br.com.devchampions.ecommerce.exceptions.handler;

import br.com.devchampions.ecommerce.util.http.HttpUrlConstants;
import org.springframework.http.HttpStatus;

import java.net.URI;

public enum ProblemDetailEnum {

    REGISTRO_NAO_LOCALIZADO("Nenhum registro localizado", URI.create(HttpUrlConstants.BASE_URL_PROBLEMS.concat("/registro-nao-localizado")), HttpStatus.NOT_FOUND),
    INFORMACAO_INVALIDA("Informação Inválida", URI.create(HttpUrlConstants.BASE_URL_PROBLEMS.concat("/informacao-invalida")), HttpStatus.BAD_REQUEST),
    INTERNAL_SERVER_ERROR("Instabilidade detectada", URI.create(HttpUrlConstants.BASE_URL_PROBLEMS.concat("/instabilidade-detectada")), HttpStatus.INTERNAL_SERVER_ERROR);

    String title;
    URI type;
    HttpStatus status;

    ProblemDetailEnum(String title, URI type, HttpStatus httpStatus) {
        this.title = title;
        this.type = type;
        this.status = httpStatus;
    }

}
