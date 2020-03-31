package ru.job4j.servlets.crud.model;

import ru.job4j.servlets.crud.storage.DBStore;
import ru.job4j.servlets.crud.storage.MemoryStore;
import ru.job4j.servlets.crud.storage.Store;
import ru.job4j.servlets.crud.User;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

/**
 * Class ValidateService.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 18.03.2020
 */
public class ValidateService implements Validate {

    private final Store persistent = DBStore.getInstance();

    private static final ValidateService INSTANCE = new ValidateService();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) throws SQLException {
        this.persistent.add(user);
    }

    @Override
    public void update(User user) throws SQLException {
        this.persistent.update(user);
    }

    @Override
    public void delete(User user) throws SQLException {
        this.persistent.delete(user);
    }

    @Override
    public List<User> findAll() throws SQLException {
        return Objects.requireNonNull(this.persistent.findAll());
    }

    @Override
    public User findById(int id) throws SQLException {
        return Objects.requireNonNull(this.persistent.findById(id));
    }
}
