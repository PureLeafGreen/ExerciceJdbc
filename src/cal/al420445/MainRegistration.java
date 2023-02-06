package cal.al420445;

import cal.al420445.dao.RegistrationDAOH2Impl;
import cal.al420445.dao.RegistrationDAOMemImpl;
import cal.al420445.service.RegistrationService;

public class MainRegistration {
    public static void main(String[] args) {
        final RegistrationDAOMemImpl dao = new RegistrationDAOMemImpl();
        final RegistrationService registrationService = new RegistrationService(dao);

        registrationService.createRegistration("Cedric", "NoSe", 18);
        registrationService.createRegistration("Marc", "NoSe", 20);

        System.out.println(registrationService.getRegistration(1));
        System.out.println(registrationService.getRegistration(2));
    }
}
