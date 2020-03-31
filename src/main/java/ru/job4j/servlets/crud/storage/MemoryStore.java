package ru.job4j.servlets.crud.storage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;
import ru.job4j.servlets.crud.User;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Class MemoryStore.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 18.03.2020
 */
@ThreadSafe
public class MemoryStore implements Store {

    private static final MemoryStore INSTANCE = new MemoryStore();

    @GuardedBy("this")
    private ConcurrentHashMap<Integer, User> storage = new ConcurrentHashMap<>();

    private MemoryStore() {
    }

    public static MemoryStore getInstance() {
        return INSTANCE;
    }

    @Override
    synchronized public boolean add(User user) {
        return this.storage.putIfAbsent(user.getId(), user) == null;
    }

    @Override
    synchronized public boolean update(User user) {
        return this.storage.put(user.getId(), user) != null;
    }

    @Override
    synchronized public boolean delete(User user) {
        return this.storage.remove(user.getId(), user) == Boolean.TRUE;
    }

    @Override
    synchronized public List<User> findAll() {
        CopyOnWriteArrayList<User> users = new CopyOnWriteArrayList<>();
        this.storage.forEachValue(Long.MAX_VALUE, users::add);
        return users;
    }

    @Override
    synchronized public User findById(int id) {
        return this.storage.getOrDefault(id, null);
    }
}
