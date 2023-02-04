package cal.al420445.dao;


import cal.al420445.model.Registration;

import java.util.List;

public interface RegistrationDAO {
    void save(Registration registration);
    void delete(Registration registration);
    Registration get(int id);
    List<Registration> getAll();
    void delete(int id);
}
