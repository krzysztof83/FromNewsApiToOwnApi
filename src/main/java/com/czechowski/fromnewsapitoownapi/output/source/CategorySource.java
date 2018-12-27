package com.czechowski.fromnewsapitoownapi.output.source;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author <a href="mailto:k.czechowski83@gmail.com">Krzysztof Czechowski</a>
 */
public class CategorySource {

    private static final Set<String> categorySet = new HashSet<>();

    static {

        String categoryInString = "business entertainment general health science sports technology";
        String[] categoryTable = categoryInString.split(" ");

        categorySet.addAll(new HashSet<>(Arrays.asList(categoryTable)));

    }

    public static Set<String> getCategorySet() {
        return categorySet;
    }
}
