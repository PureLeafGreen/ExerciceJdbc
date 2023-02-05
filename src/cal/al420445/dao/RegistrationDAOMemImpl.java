package cal.al420445.dao;

import cal.al420445.model.Registration;

import java.util.List;
import java.util.HashMap;

public class RegistrationDAOMemImpl implements RegistrationDAO {
    HashMap<Long,Registration> registrations = new HashMap<Long,Registration>();
    @Override
    public void save(Registration registration) {
        registrations.put(registration.getId(), registration);
    }

    @Override
    public void delete(Registration registration) {
        registrations.remove(registration.getId());
    }

    @Override
    public Registration get(int id) {
        return registrations.get(Long.parseLong(Integer.toString(id)));
    }

    @Override
    public List<Registration> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
