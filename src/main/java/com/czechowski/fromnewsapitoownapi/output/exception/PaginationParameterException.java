package com.czechowski.fromnewsapitoownapi.output.exception;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class PaginationParameterException extends RuntimeException {

    private String pageInString;
    private String pageSizeInString;

    public PaginationParameterException(String pageInString, String pageSizeInString) {
        this.pageInString = pageInString;
        this.pageSizeInString = pageSizeInString;
    }

    public String getPageInString() {
        return pageInString;
    }

    public String getPageSizeInString() {
        return pageSizeInString;
    }
}
