package sf.hack.day.proximity.scanner.services;

import sf.hack.day.proximity.scanner.model.Policy;

import java.util.HashMap;
import java.util.Map;
import sf.hack.day.proximity.scanner.model.Address;
import sf.hack.day.proximity.scanner.model.Vehicle;

/**
 * Created by aaron on 6/15/16.
 */

public final class PolicyRetrieval {
    private static Map<String, Policy> policies = new HashMap<>();
    static {
        policies.put("968A3179", createIvan());
        policies.put("4626BC6D", createAmber());
    }


    public static Policy retrievePolicy(String UUID) {
        return policies.get(UUID);
    }

    private static Policy createIvan() {
        Policy policy = new Policy();
        policy.id = "537 3616-F27-13A";
        policy.customerName = "Hall, Ivan & Michelle";
        policy.agent = "John Smith";
        policy.effectiveDate = "6/16/2016";

        Address address = new Address();
        address.address1 = "1 Boulder Dr.";
        address.city = "Carlock";
        address.state = "IL";
        address.zip = "61725";
        policy.address = address;

        Vehicle vehicle = new Vehicle();
        vehicle.vin = "2GTEK63N451117733";
        vehicle.model="K1500";
        vehicle.make="GMC";
        vehicle.year="2005";
        policy.vehicle = vehicle;

        return policy;
    }

    private static Policy createAmber() {
        Policy policy = new Policy();
        policy.id = "537 3616-F27-13A";
        policy.customerName = "Wyatt, Mike & Amber L";
        policy.agent = "John Smith";
        policy.effectiveDate = "6/16/2016";

        Address address = new Address();
        address.address1 = "1 Boulder Dr.";
        address.city = "Carlock";
        address.state = "IL";
        address.zip = "61725";
        policy.address = address;

        Vehicle vehicle = new Vehicle();
        vehicle.vin = "2GTEK63N451117733";
        vehicle.model="K1500";
        vehicle.make="GMC";
        vehicle.year="2005";
        policy.vehicle = vehicle;

        return policy;
    }
}
