package ru.job4j.dream.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Store {

    private static final Store INSTANCE = new Store();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", "описание_1"));
        posts.put(2, new Post(2, "Middle Java Job", "описание_2"));
        posts.put(3, new Post(3, "Senior Java Job", "описание_3"));
    }

    public static Store instOf() {
        return INSTANCE;
    }

    public Collection<Post> findAll() {
        return posts.values();
    }
}
