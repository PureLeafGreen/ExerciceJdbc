package cal.voyage.service;


import cal.voyage.modele.Passenger;
import cal.voyage.persistence.PassengerDAO;

public class ServiceVoyage {
    private final PassengerDAO jdbcVoyage;

    public ServiceVoyage(PassengerDAO jdbcVoyage) {
        this.jdbcVoyage = jdbcVoyage;
    }

    public void save(Passenger passenger) {
        jdbcVoyage.save(passenger);
        jdbcVoyage.save(passenger);
    }

    public Passenger getPassenger(long passengerId) {
        return jdbcVoyage.getPassenger(passengerId);
    }
}
