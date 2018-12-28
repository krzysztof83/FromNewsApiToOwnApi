package com.czechowski.fromnewsapitoownapi.output.service;

import com.czechowski.fromnewsapitoownapi.output.exception.PaginationParameterException;
import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCategoryParameterException;
import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCountryAndCategoryParameterException;
import com.czechowski.fromnewsapitoownapi.output.exception.UnsupportedCountryParameterException;
import com.czechowski.fromnewsapitoownapi.output.source.CategorySource;
import com.czechowski.fromnewsapitoownapi.output.source.CountrySource;
import org.springframework.util.StringUtils;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class StrategyWithUnsupported {

    public static void handleUnsupported(String country, String category, String pageInString, String pageSizeInString) throws PaginationParameterException {
        StrategyWithUnsupportedState state = determineUnsaportedState(country, category, pageInString, pageSizeInString);

        switch (state) {
            case OK_DO_SERVICE:
                break;
            case COUNTRY_ERROR:
                throw new UnsupportedCountryParameterException(country);
            case CATEGORY_ERROR:
                throw new UnsupportedCategoryParameterException(category);
            case COUNTRY_AND_CATEGORYERROR:
                throw new UnsupportedCountryAndCategoryParameterException(country, category);
            case PAGINATION_ERROR:
                throw new PaginationParameterException(pageInString, pageSizeInString);
        }

    }

    private static StrategyWithUnsupportedState determineUnsaportedState(String country, String category, String pageInString, String pageSizeInString) {

        final boolean pageInStringIsANumber = checkIsParsableToInt(pageInString);
        final boolean pageSizeInStringIsANumber = checkIsParsableToInt(pageSizeInString);

        final boolean unsupportedCountry = checkCountry(country);
        final boolean unsupportedCategory = checkCategory(category);

        if (!pageInStringIsANumber || !pageSizeInStringIsANumber) {
            return StrategyWithUnsupportedState.PAGINATION_ERROR;
        }

        if (!unsupportedCountry && !unsupportedCategory)
            return StrategyWithUnsupportedState.OK_DO_SERVICE;

        if (unsupportedCountry && !unsupportedCategory) {
            return StrategyWithUnsupportedState.COUNTRY_ERROR;
        }

        if (!unsupportedCountry && unsupportedCategory) {
            return StrategyWithUnsupportedState.CATEGORY_ERROR;
        }

        if (unsupportedCountry && unsupportedCategory) {
            return StrategyWithUnsupportedState.COUNTRY_AND_CATEGORYERROR;
        }

        return null;
    }

    private static boolean checkCountry(String country) {
        return (!StringUtils.isEmpty(country) && !CountrySource.getCountrySet().contains(country));
    }

    private static boolean checkCategory(String category) {
        return (!StringUtils.isEmpty(category) && !CategorySource.getCategorySet().contains(category));
    }

    private static boolean checkIsParsableToInt(String string) {
        try {
            double d = Integer.parseInt(string);
        } catch (NumberFormatException | NullPointerException nfe) {
            return false;
        }
        return true;
    }

    private enum StrategyWithUnsupportedState {
        COUNTRY_ERROR, CATEGORY_ERROR, COUNTRY_AND_CATEGORYERROR, OK_DO_SERVICE, PAGINATION_ERROR;
    }

}
