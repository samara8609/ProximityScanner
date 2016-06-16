package sf.hack.day.proximity.scanner.services;

import java.util.HashMap;
import java.util.Map;

import sf.hack.day.proximity.scanner.model.Vehicle;

/**
 * Created by aaron on 6/16/16.
 */

public final class VehicleRetrieval {
    private static Map<String, Vehicle> vehicles = new HashMap<>();

    static {
        vehicles.put("968A3179", createVehicle1());
        vehicles.put("4626BC6D", createVehicle2());
    }

    private VehicleRetrieval() {}

    public static Vehicle retrieve(String UUID) {
        return vehicles.get(UUID);
    }

    private static Vehicle createVehicle1() {
        Vehicle vehicle = new Vehicle();
        vehicle.vin = "2GTEK63N451117733";
        vehicle.model="K1500";
        vehicle.make="GMC";
        vehicle.year="2005";
        vehicle.color="Silver";
        vehicle.trim="LT";
        vehicle.bodyType="Crew Cab";
        vehicle.engine="5.3 Litre V8";
        return vehicle;
    }

    private static Vehicle createVehicle2() {
        Vehicle vehicle = new Vehicle();
        vehicle.vin = "2GTEK63N451117324";
        vehicle.model="Explorer";
        vehicle.make="Ford";
        vehicle.year="2016";
        vehicle.color="Black";
        vehicle.trim="XLT";
        vehicle.bodyType="4 Door Crossover";
        vehicle.engine="3.5 Litre V6";
        return vehicle;
    }
}
