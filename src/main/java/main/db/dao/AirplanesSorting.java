package main.db.dao;

import java.util.Map;
import java.util.TreeMap;

public class AirplanesSorting {
    public static final String SORT__FROM_BIG = "fromBig";
    public static final String SORT__FROM_SMALL = "fromSmall";
    public static final String SORT__BY_ALPHABET = "byAlphabet";
    public static final String SORT__BY_TYPE = "byType";

    private static final String SORT__QUERY_FROM_BIG =
            "SELECT * FROM airplanes " +
            "inner join specs on airplane_specs_id = specs_id " +
            "inner join photos on airplane_photo_id = photo_id " +
            "ORDER BY specs_len*specs_wings_span*specs_height DESC;";
    private static final String SORT__QUERY_FROM_SMALL =
            "SELECT * FROM airplanes " +
                    "inner join specs on airplane_specs_id = specs_id " +
                    "inner join photos on airplane_photo_id = photo_id " +
                    "ORDER BY specs_len*specs_wings_span*specs_height ASC;";
    private static final String SORT__QUERY_BY_ALPHABET =
            "SELECT * FROM airplanes ORDER BY airplane_name ASC";
    private static final String SORT__QUERY_BY_TYPE =
            "SELECT * FROM airplanes ORDER BY airplane_type ASC";

    private static final Map<String, String> sorts = new TreeMap<>();
    static {
        sorts.put(SORT__FROM_BIG, SORT__QUERY_FROM_BIG);
        sorts.put(SORT__FROM_SMALL, SORT__QUERY_FROM_SMALL);
        sorts.put(SORT__BY_ALPHABET, SORT__QUERY_BY_ALPHABET);
        sorts.put(SORT__BY_TYPE, SORT__QUERY_BY_TYPE);
    }

    public static String getSortQuery(String sorting) {
        return sorts.get(sorting);
    }
}
