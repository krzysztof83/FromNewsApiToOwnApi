package com.czechowski.fromnewsapitoownapi.output.exception;

import java.io.Serializable;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class UnsupportedCategoryParameterException extends RuntimeException implements Serializable {

    private String category;

    public UnsupportedCategoryParameterException(String category) {
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
