package com.works.soworks.soworksapi.exceptionhandler;

import com.works.soworks.soworksapi.domain.exception.ClientException;
import com.works.soworks.soworksapi.domain.exception.EntityNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import java.time.OffsetDateTime;
import java.util.ArrayList;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> handleEntityNotFoundException(EntityNotFoundException entityNotFoundException, WebRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        Exception exception = createExceptionBody(entityNotFoundException, status);
        return handleExceptionInternal(entityNotFoundException, exception, new HttpHeaders(), status, request);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(ClientException.class)
    public ResponseEntity<Object> handleClientException(ClientException clientException, WebRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        Exception exception = createExceptionBody(clientException, status);
        return handleExceptionInternal(clientException, exception, new HttpHeaders(), status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        Exception exception = new Exception();
        exception.setStatus(status.value());
        exception.setMessage("Invalid field(s)");
        exception.setTime(OffsetDateTime.now());
        exception.setFields(generateFieldsErrorArray(ex));
        return super.handleExceptionInternal(ex, exception, headers, status, request);
    }

    private ArrayList<Exception.Field> generateFieldsErrorArray(MethodArgumentNotValidException notValidException){
        var fields = new ArrayList<Exception.Field>();
        for(ObjectError error: notValidException.getBindingResult().getAllErrors()){
            fields.add(new Exception.Field(((FieldError) error).getField(), error.getDefaultMessage()));
        }
        return fields;
    }

    private Exception createExceptionBody(RuntimeException runtimeException, HttpStatus status){
        Exception exception = new Exception();
        exception.setStatus(status.value());
        exception.setMessage(runtimeException.getMessage());
        exception.setTime(OffsetDateTime.now());

        return exception;
    }
}
