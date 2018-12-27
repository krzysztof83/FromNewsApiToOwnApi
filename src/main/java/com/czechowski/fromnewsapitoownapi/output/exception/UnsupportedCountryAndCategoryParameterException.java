package com.czechowski.fromnewsapitoownapi.output.exception;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class UnsupportedCountryAndCategoryParameterException extends RuntimeException {

    private String country;
    private String category;

    public UnsupportedCountryAndCategoryParameterException(String country, String category) {
        this.country = country;
        this.category = category;
    }

    public String getCountry() {
        return country;
    }

    public String getCategory() {
        return category;
    }
}
