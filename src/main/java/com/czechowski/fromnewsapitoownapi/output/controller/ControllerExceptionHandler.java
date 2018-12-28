package com.czechowski.fromnewsapitoownapi.output.controller;

import com.czechowski.fromnewsapitoownapi.output.exception.PaginationParameterException;
import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCategoryParameterException;
import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCountryAndCategoryParameterException;
import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCountryParameterException;
import com.czechowski.fromnewsapitoownapi.output.model.error.PaginationParameterError;
import com.czechowski.fromnewsapitoownapi.output.model.error.UnsupportedCategoryError;
import com.czechowski.fromnewsapitoownapi.output.model.error.UnsupportedCountryAndCategoryError;
import com.czechowski.fromnewsapitoownapi.output.model.error.UnsupportedCountryError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */

@ControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsupportedCountryParameterException.class)
    public ResponseEntity<?> handleUnsupportedCountry(UnsupportedCountryParameterException exception) {

        UnsupportedCountryError unsupportedCountryError = new UnsupportedCountryError(exception);

        return new ResponseEntity<>(unsupportedCountryError, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsupportedCategoryParameterException.class)
    public ResponseEntity<?> handleUnsupportedCategory(UnsupportedCategoryParameterException exception) {

        UnsupportedCategoryError unsupportedCategoryError = new UnsupportedCategoryError(exception);

        return new ResponseEntity<>(unsupportedCategoryError, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UnsupportedCountryAndCategoryParameterException.class)
    public ResponseEntity<?> handleUnsupportedCountry(UnsupportedCountryAndCategoryParameterException exception) {

        UnsupportedCountryAndCategoryError unsupportedCountryAndCategoryError = new UnsupportedCountryAndCategoryError(exception);

        return new ResponseEntity<>(unsupportedCountryAndCategoryError, HttpStatus.BAD_REQUEST);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PaginationParameterException.class)
    public ResponseEntity<?> handleUnsupportedCountry(PaginationParameterException exception) {

        PaginationParameterError paginationParameterError = new PaginationParameterError(exception);

        return new ResponseEntity<>(paginationParameterError, HttpStatus.BAD_REQUEST);
    }
}
