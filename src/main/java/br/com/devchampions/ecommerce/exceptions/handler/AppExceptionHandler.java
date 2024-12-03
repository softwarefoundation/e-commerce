package br.com.devchampions.ecommerce.exceptions.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.dao.InvalidDataAccessResourceUsageException;
import org.springframework.dao.DataIntegrityViolationException;

import java.text.MessageFormat;
import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @Autowired
    private MessageSource messageSource;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        ProblemDetail problemDetail = prepararProblemDetail(ProblemDetailEnum.INFORMACAO_INVALIDA);

        ex.getBindingResult().getFieldErrors().forEach(e -> {
            String msgTemplate = messageSource.getMessage(e, LocaleContextHolder.getLocale());
            String detail = MessageFormat.format(msgTemplate.concat(": {0}"), handleRejectedValue(e.getRejectedValue()));
            problemDetail.getDetail().add(detail);
        });

        problemDetail.setFieldErrors(ex.getFieldErrors());

        return prepararResponseEntity(problemDetail);

    }

    @ExceptionHandler({InvalidDataAccessResourceUsageException.class})
    public ResponseEntity<?> handleInvalidDataAccessResourceUsageException(InvalidDataAccessResourceUsageException e) {
        ProblemDetail problemDetail = prepararProblemDetail(ProblemDetailEnum.INTERNAL_SERVER_ERROR);
        problemDetail.getDetail().add(e.getMessage());
        return prepararResponseEntity(problemDetail);
    }

    @ExceptionHandler({DataIntegrityViolationException.class})
    public ResponseEntity<?> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        ProblemDetail problemDetail = prepararProblemDetail(ProblemDetailEnum.INTERNAL_SERVER_ERROR);
        problemDetail.getDetail().add(e.getMessage());
        printStackTrace(problemDetail, e);
        return prepararResponseEntity(problemDetail);
    }


    private ProblemDetail prepararProblemDetail(ProblemDetailEnum problemDetailEnum) {
        ProblemDetail problemDetail = new ProblemDetail();
        problemDetail.setTitle(problemDetailEnum.title);
        problemDetail.setType(problemDetailEnum.type);
        problemDetail.setStatus(problemDetailEnum.status.value());
        return problemDetail;
    }

    private ResponseEntity<Object> prepararResponseEntity(ProblemDetail detail) {
        return ResponseEntity.status(detail.getStatus()).contentType(MediaType.APPLICATION_PROBLEM_JSON).body(detail);
    }


    private String handleRejectedValue(Object rejectedValue) {
        if (rejectedValue == null) {
            return "";
        }
        return rejectedValue.toString();
    }


    private void printStackTrace(ProblemDetail problemDetail, Exception e) {
        log.error(MessageFormat.format("Rastreamento de Erro | UUID: {0} : {1}", problemDetail.getUuid().toString(), e.getClass().getName()), e);
    }

}
