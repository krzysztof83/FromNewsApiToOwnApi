package com.czechowski.fromnewsapitoownapi.output.exception;

import java.io.Serializable;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class UnsupportedCountryParameterException extends RuntimeException  implements Serializable {

    private String country;

    public UnsupportedCountryParameterException(String country) {
        this.country = country;
    }

    public String getCountry() {
        return country;
    }
}
