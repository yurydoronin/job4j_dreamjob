package ru.job4j.servlets.crud.model;

import ru.job4j.servlets.crud.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Class Validate.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 18.03.2020
 */
public interface Validate {

    void add(User user) throws SQLException;

    void update(User user) throws SQLException;

    void delete(User user) throws SQLException;

    List<User> findAll() throws SQLException;

    User findById(int id) throws SQLException;
}
