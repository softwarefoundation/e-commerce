package br.com.devchampions.ecommerce.security.http;

public enum HttpAllowedOriginsEnum {

    ECONMMERCE_FRONTEND("http://localhost:4200");

    private String url;

    HttpAllowedOriginsEnum(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
