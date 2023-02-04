package cal.voyage.persistence;

import cal.voyage.modele.Passenger;

public interface PassengerDAO {
    public void createDatabase();
    public void save(Passenger passenger);
    public Passenger getPassenger(long passengerId);
}
