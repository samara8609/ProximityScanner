package sf.hack.day.proximity.scanner.services;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by aaron on 6/16/16.
 */

public final class QuoteRetrieval {

    private static Map<String, Double> people = new HashMap<>();

    static {
        people.put("968A3179", new Double(1.01));
        people.put("4626BC6D", new Double(1.02));
    }

    private QuoteRetrieval(){}

    public static double retrieve(String UUID, String coverage) {
        return 0.00;

    }

}
