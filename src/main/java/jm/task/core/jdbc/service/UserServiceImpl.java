package jm.task.core.jdbc.service;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao USERDAO;

    public UserServiceImpl() {
        this.USERDAO = new UserDaoJDBCImpl();
    }
    @Override
    public void createUsersTable() {
        USERDAO.createUsersTable();
    }

    @Override
    public void dropUsersTable() {
        USERDAO.dropUsersTable();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        USERDAO.saveUser(name, lastName, age);
    }

    @Override
    public void removeUserById(long id) {
        USERDAO.removeUserById(id);

    }

    @Override
    public List<User> getAllUsers()
    {
        return USERDAO.getAllUsers();
    }

    @Override
    public void cleanUsersTable() {
        USERDAO.cleanUsersTable();
    }
}
