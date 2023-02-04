package cal.al420445.service;

import cal.al420445.dao.RegistrationDAO;
import cal.al420445.model.Registration;

public class RegistrationService {

    private final RegistrationDAO dao;

    public RegistrationService(RegistrationDAO dao){
        this.dao = dao;
    }
    public void createRegistration(String firstName, String lastName, int age) {
        dao.save(new Registration(lastName, firstName, age));
    }

    public Registration getRegistration(int registrationId) {
        return dao.get(registrationId);
    }
}
