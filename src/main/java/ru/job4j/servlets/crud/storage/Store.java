package ru.job4j.servlets.crud.storage;

import ru.job4j.servlets.crud.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Class Store.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 18.03.2020
 */
public interface Store {

    boolean add(User user) throws SQLException;

    boolean update(User user) throws SQLException;

    boolean delete(User user) throws SQLException;

    List<User> findAll() throws SQLException;

    User findById(int id) throws SQLException;
}
