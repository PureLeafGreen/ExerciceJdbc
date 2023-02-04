package cal.al420445.dao;

import cal.al420445.model.Registration;
import cal.al420445.utils.JDBCclass;

import java.util.List;

public class RegistrationDAOH2Impl implements RegistrationDAO {
    @Override
    public void save(Registration registration) {
        JDBCclass.save(registration);
    }

    @Override
    public void delete(Registration registration) {

    }

    @Override
    public Registration get(int id) {
        return JDBCclass.getRegistration(id);
    }

    @Override
    public List<Registration> getAll() {
        return null;
    }

    @Override
    public void delete(int id) {

    }
}
