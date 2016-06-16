package sf.hack.day.proximity.scanner.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by aaron on 6/16/16.
 */

public final class QuoteRetrieval {

    private static Map<String, Double> people = new HashMap<>();

    private static Map<String, Double> coverages = new HashMap<>();

    static {
        people.put("968A3179", new Double(1.01));
        people.put("4626BC6D", new Double(1.02));

        coverages.put("25/50 Coverage", new Double(1.01));
        coverages.put("50/100 Coverage", new Double(1.02));
        coverages.put("100/300 Coverage", new Double(1.03));
    }

    private QuoteRetrieval(){}

    public static double retrieve(String UUID, String coverage) {
        double value = 90 * people.get(UUID) * coverages.get(coverage);
        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(2, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }

}
