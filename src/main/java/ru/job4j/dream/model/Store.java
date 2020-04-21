package ru.job4j.dream.model;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Class Store.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 21.04.2020
 */
public class Store {

    private static final Store INSTANCE = new Store();

    private final Map<Integer, Post> posts = new ConcurrentHashMap<>();

    private final Map<Integer, Candidate> candidates = new ConcurrentHashMap<>();

    private final static AtomicInteger POST_ID = new AtomicInteger(4);

    private Store() {
        posts.put(1, new Post(1, "Junior Java Job", "описание_1"));
        posts.put(2, new Post(2, "Middle Java Job", "описание_2"));
        posts.put(3, new Post(3, "Senior Java Job", "описание_3"));
        candidates.put(1, new Candidate(1, "Junior Java"));
        candidates.put(2, new Candidate(2, "Middle Java"));
        candidates.put(3, new Candidate(3, "Senior Java"));
    }

    public static Store instOf() {
        return INSTANCE;
    }

    public Collection<Post> findAllPosts() {
        return posts.values();
    }

    public Collection<Candidate> findAllCandidates() {
        return candidates.values();
    }

    public void save(Post post) {
        post.setId(POST_ID.incrementAndGet());
        posts.put(post.getId(), post);
    }

    public void save(Candidate candidate) {
        candidate.setId(POST_ID.incrementAndGet());
        candidates.put(candidate.getId(), candidate);
    }
}
