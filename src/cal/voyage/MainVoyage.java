package cal.voyage;


import cal.voyage.modele.Passenger;
import cal.voyage.persistence.PassengerDAOInMemory;
import cal.voyage.service.ServiceVoyage;

public class MainVoyage {
    public static void main(String[] args) {

        var passenger = new Passenger(1, "Smith", "John");
        ServiceVoyage serviceVoyage = new ServiceVoyage(new PassengerDAOInMemory());
        serviceVoyage.save(passenger);
        var passenger2 = serviceVoyage.getPassenger(1L);
        System.out.println(passenger2);
    }
}
