package ru.job4j.servlets.crud;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

/**
 * Class User.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 16.03.2020
 */
public class User {

    private int id;

    private String name;

    private final LocalDateTime createDate;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
        this.createDate = LocalDateTime.now();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id
                && name.equals(user.name)
                && createDate.equals(user.createDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, createDate);
    }

    @Override
    public String toString() {
        return String.format("User: id %s, name %s, created %s",
                id, name, createDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}
