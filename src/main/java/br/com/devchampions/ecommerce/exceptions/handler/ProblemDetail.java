package br.com.devchampions.ecommerce.exceptions.handler;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.FieldError;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
public class ProblemDetail {

    @Setter(AccessLevel.NONE)
    private LocalDateTime timestamp;
    @Setter(AccessLevel.NONE)
    private UUID uuid;
    private Integer status;
    private String title;
    private URI type;
    private List<String> detail;
    private List<FieldError> fieldErrors;

    public ProblemDetail() {
        this.timestamp = LocalDateTime.now();
        this.uuid = UUID.randomUUID();
    }

    public List<String> getDetail() {
        if (Objects.isNull(this.detail)) {
            this.detail = new ArrayList<>();
        }
        return detail;
    }


}
