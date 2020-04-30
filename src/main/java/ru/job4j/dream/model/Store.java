package ru.job4j.dream.model;

import java.util.Collection;

/**
 * Interface Store.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 28.04.2020
 */
public interface Store {

    Collection<User> findAllUsers();

    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    User userFindById(int id);

    Post postFindById(int id);

    Candidate candidateFindById(int id);

    User findByEmail(String email);

    void save(User user);

    void save(Post post);

    void save(Candidate candidate);

}
