package ru.job4j.servlets.crud;

import java.util.List;

/**
 * Class Validate.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 18.03.2020
 */
public interface Validate {

    void add(User user);

    void update(User user);

    void delete(User user);

    List<User> findAll();

    User findById(int id);
}
