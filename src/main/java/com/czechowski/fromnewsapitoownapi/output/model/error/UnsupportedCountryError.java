package com.czechowski.fromnewsapitoownapi.output.model.error;

import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCountryParameterException;
import com.czechowski.fromnewsapitoownapi.output.source.CountrySource;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class UnsupportedCountryError extends AbstractUnsupportedError {

    public UnsupportedCountryError(UnsupportedCountryParameterException unsupportedCountryParameterException) {

        this.status = HttpStatus.BAD_REQUEST;
        this.timestamp = LocalDateTime.now();
        this.message = "Unsupported country parameter - " + unsupportedCountryParameterException.getCountry();
        this.debugMessage = "Try parameter from possibleCountry list";
        this.additionalInfo.put("possibleCountry", CountrySource.getCountrySet());

    }
}
