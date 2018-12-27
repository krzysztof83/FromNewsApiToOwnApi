package com.czechowski.fromnewsapitoownapi.output.model.error;

import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCountryAndCategoryParameterException;
import com.czechowski.fromnewsapitoownapi.output.source.CountrySource;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class UnsupportedCountryAndCategoryError extends AbstractUnsupportedError {

    public UnsupportedCountryAndCategoryError(UnsupportedCountryAndCategoryParameterException e) {

        this.status = HttpStatus.BAD_REQUEST;
        this.timestamp = LocalDateTime.now();
        this.message = "Unsupported country and category parameter - " + e.getCountry() + " , " + e.getCategory();
        this.debugMessage = "Try parameter from possibleCountry list and possibleCategory list";
        this.additionalInfo.put("possibleCountry", CountrySource.getCountrySet());
        this.additionalInfo.put("possibleCategory", CountrySource.getCountrySet());

    }
}
