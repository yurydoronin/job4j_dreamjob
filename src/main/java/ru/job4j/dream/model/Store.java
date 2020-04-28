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

    Collection<Post> findAllPosts();

    Collection<Candidate> findAllCandidates();

    void save(Post post);

    void save(Candidate candidate);

    Post postFindById(int id);

    Candidate candidateFindById(int id);

}
