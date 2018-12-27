package com.czechowski.fromnewsapitoownapi.output.source;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class CountrySource {

    private static final Set<String> countrySet = new HashSet<>();

    static {

        String countryInString = "ae ar at au be bg br ca ch cn co cu cz de eg fr gb gr hk hu id ie il in it jp kr lt lv ma mx my ng nl no nz ph pl pt ro rs ru sa se sg si sk th tr tw ua us ve za";
        String[] countryTable = countryInString.split(" ");

        countrySet.addAll(new HashSet<>(Arrays.asList(countryTable)));

    }

    public static Set<String> getCountrySet() {
        return countrySet;
    }

}
