package br.com.devchampions.ecommerce.exceptions;

import java.text.MessageFormat;

public class RegistroNaoLocalizadoException extends RuntimeException {

    public RegistroNaoLocalizadoException(Object parametro) {
        super(MessageFormat.format("Nenhum registro localizado utilizando o parâmetro: {0} na consulta", parametro));
    }

    public RegistroNaoLocalizadoException(String message) {
        super(message);
    }

}
