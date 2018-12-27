package com.czechowski.fromnewsapitoownapi.output.model.error;

import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCategoryParameterException;
import com.czechowski.fromnewsapitoownapi.output.source.CountrySource;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class UnsupportedCategoryError extends AbstractUnsupportedError {

    public UnsupportedCategoryError(UnsupportedCategoryParameterException unsupportedCategoryParameterException) {

        this.status = HttpStatus.BAD_REQUEST;
        this.timestamp = LocalDateTime.now();
        this.message = "Unsupported category parameter - " + unsupportedCategoryParameterException.getCategory();
        this.debugMessage = "Try parameter from possibleCategory list";
        this.getAdditionalInfo().put("possibleCategory", CountrySource.getCountrySet());

    }
}
