package com.czechowski.fromnewsapitoownapi.output.exception;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class UnsupportedCategoryParameterException extends RuntimeException {

    private String category;

    public UnsupportedCategoryParameterException(String country) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
