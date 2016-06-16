package sf.hack.day.proximity.scanner.model;

/**
 * Created by aaron on 6/15/16.
 */

public class MockData {

    public String policyId;

    public String agent;

    public String effectiveDate;

    public String claimId;

    public String customerName;

    public String street;

    public String city;

    public String vin;

    private String year;

    private String make;

    private String model;

    private String color;

    private String trim;

    private String bodyType;

    private String engine;

    private String quote;

    public MockData (String id) {
        policyId = "537 3616-F27-13A";
        agent = "John Smith";
        effectiveDate = "6/16/2016";
        customerName = "Hall, Ivan & Michelle";
        vin = "2GTEK63N451117733";
        model="K1500";
        make="GMC";
        year="2005";
        street = "1 Boulder Dr.";
        city = "Carlock, IL 61725";
    }
}
