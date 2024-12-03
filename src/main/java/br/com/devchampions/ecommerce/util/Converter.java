package br.com.devchampions.ecommerce.util;

import org.modelmapper.ModelMapper;

public class Converter {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();
    private static final Converter CONVERTER = new Converter();

    private Converter() {
    }

    public static Converter getInstance() {
        return CONVERTER;
    }

    public <S, T> T converter(S source, Class<T> destination) {
        return MODEL_MAPPER.map(source, destination);
    }

}
