package cal.voyage.persistence;


import cal.voyage.modele.Passenger;

import java.util.HashMap;
import java.util.Map;

public class PassengerDAOInMemory implements PassengerDAO {
    Map<Long, Passenger> passengerMap = new HashMap<>();
    @Override
    public void createDatabase() {

    }

    @Override
    public void save(Passenger passenger) {
        passengerMap.put(passenger.getId(), passenger);
    }

    @Override
    public Passenger getPassenger(long passengerId) {
        return passengerMap.get(passengerId);
    }
}
