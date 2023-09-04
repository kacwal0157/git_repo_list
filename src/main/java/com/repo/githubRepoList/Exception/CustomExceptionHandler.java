package com.repo.githubRepoList.Exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(HttpClientErrorException.NotAcceptable.class)
    public ResponseEntity<Object> handleUnsupportedAcceptHeaderException(HttpClientErrorException.NotAcceptable exception, WebRequest webRequest) {
        CustomException customException = new CustomException();
        customException.setResponseCode(406);
        customException.setWhyHasItHappened("Unsupported Accept header. Please use 'Accept: application/json'");

        return new ResponseEntity<>(customException, new HttpHeaders(), HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(HttpServerErrorException.InternalServerError.class)
    public ResponseEntity<Object> handleInternalServerErrorException(HttpServerErrorException.InternalServerError exception, WebRequest webRequest) {
        CustomException customException = new CustomException();
        customException.setResponseCode(500);
        customException.setWhyHasItHappened("Something went wrong while connecting to the server");

        return new ResponseEntity<>(customException, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(HttpClientErrorException.NotFound.class)
    public ResponseEntity<Object> handleUserNotFoundException(HttpClientErrorException.NotFound exception, WebRequest webRequest) {
        CustomException customException = new CustomException();
        customException.setResponseCode(404);
        customException.setWhyHasItHappened("User not found");

        return new ResponseEntity<>(customException, new HttpHeaders(), HttpStatus.NOT_FOUND);
    }
}
