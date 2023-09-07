package com.repo.githubRepoList.exception;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<ExceptionMessage> handleUserNotFoundException(UserNotFoundException exception, WebRequest webRequest) {
        ExceptionMessage exceptionMessage = ExceptionMessage.builder().responseCode(HttpStatus.NOT_FOUND.value()).whyHasItHappened(exception.getMessage()).build();
        return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UnsupportedAcceptHeaderException.class)
    public ResponseEntity<String> handleUnsupportedAcceptHeaderException(UnsupportedAcceptHeaderException exception) {
        ExceptionMessage exceptionMessage = ExceptionMessage.builder()
                .responseCode(HttpStatus.NOT_ACCEPTABLE.value())
                .whyHasItHappened(exception.getMessage())
                .build();

        try {
            XmlMapper xmlMapper = new XmlMapper();
            String xmlResponse = xmlMapper.writeValueAsString(exceptionMessage);
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(xmlResponse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

}
