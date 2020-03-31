package ru.job4j.servlets.crud.model;

import ru.job4j.servlets.crud.storage.MemoryStore;
import ru.job4j.servlets.crud.storage.Store;
import ru.job4j.servlets.crud.User;

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

    private final Store persistent = MemoryStore.getInstance();

    private static final ValidateService INSTANCE = new ValidateService();

    private ValidateService() {
    }

    public static ValidateService getInstance() {
        return INSTANCE;
    }

    @Override
    public void add(User user) {
        this.persistent.add(user);
    }

    @Override
    public void update(User user) {
        this.persistent.update(user);
    }

    @Override
    public void delete(User user) {
        this.persistent.delete(user);
    }

    @Override
    public List<User> findAll() {
        return Objects.requireNonNull(this.persistent.findAll());
    }

    @Override
    public User findById(int id) {
        return Objects.requireNonNull(this.persistent.findById(id));
    }
}
