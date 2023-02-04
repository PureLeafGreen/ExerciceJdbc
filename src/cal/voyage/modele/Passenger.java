package cal.voyage.modele;

public class Passenger {
    private long id;
    private final String lastName;
    private final String firstName;

    public Passenger(long id, String lastName, String firstname) {
        this.id = id;
        this.lastName = lastName;
        this.firstName = firstname;
    }

    public long getId() {
        return id;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    @Override
    public String toString() {
        return "Passenger{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }

}
