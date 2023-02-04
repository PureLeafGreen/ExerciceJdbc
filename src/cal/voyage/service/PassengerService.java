package cal.voyage.service;

import cal.voyage.modele.Passenger;
import cal.voyage.persistence.PassengerDAO;


public class PassengerService {

    private PassengerDAO dao;

    public PassengerService(PassengerDAO dao){
        this.dao = dao;
    }

    public void createOrUpdatePassenger(
            String name, String lastName) {
        Passenger passenger = new Passenger(1, name, lastName);
        dao.save(passenger);
    }


}
