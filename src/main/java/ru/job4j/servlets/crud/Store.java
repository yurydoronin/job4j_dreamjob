package ru.job4j.servlets.crud;

import java.util.List;

/**
 * Class Store.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 18.03.2020
 */
public interface Store {

    boolean add(User user);

    boolean update(User user);

    boolean delete(User user);

    List<User> findAll();

    User findById(int id);
}
