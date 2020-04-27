package ru.job4j.spring.police.model;

import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Class Accident.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 19.04.2020
 */
@Component
public class Accident {

    private int id;
    private String name;
    private String text;
    private String address;

    public Accident(int id, String name, String text, String address) {
        this.id = id;
        this.name = name;
        this.text = text;
        this.address = address;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id
                && name.equals(accident.name)
                && Objects.equals(text, accident.text)
                && address.equals(accident.address);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
