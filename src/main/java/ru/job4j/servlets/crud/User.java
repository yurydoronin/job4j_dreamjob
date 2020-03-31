package ru.job4j.servlets.crud;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Objects;

/**
 * Class User.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 16.03.2020
 */
public class User {

    /**
     *
     */
    private final int id;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private LocalDateTime createDate;

    public User(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
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
