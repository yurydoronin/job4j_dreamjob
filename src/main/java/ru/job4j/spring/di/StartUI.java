package ru.job4j.spring.di;

import org.springframework.stereotype.Component;

/**
 * Class StartUI.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 10.04.2020
 */
@Component
public class StartUI {

    private final Store store;

    public StartUI(Store store) {
        this.store = store;
    }

    public void add(String value) {
        store.add(value);
    }

    public void print() {
        for (String value : store.getAll()) {
            System.out.println(value);
        }
    }
}
