package ru.job4j.dream.model;

/**
 * Class PsqlMain.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 28.04.2020
 */
public class PsqlMain {

    public static void main(String[] args) {
        Store store = PsqlStore.instOf();

        store.save(new Post(0, "C# developer", "description"));
        store.save(new Post(0, "Java developer", "description"));
        store.findAllPosts().forEach(p -> System.out.println(String.format("%s %s", p.getId(), p.getName())));
        Post post = store.postFindById(1);
        System.out.println(post);
        System.out.println("");

        store.save(new Candidate(0, "Коля"));
        store.save(new Candidate(0, "Вася"));
        store.findAllCandidates().forEach(c -> System.out.println(String.format("%s %s", c.getId(), c.getName())));
        Candidate candidate = store.candidateFindById(1);
        System.out.println(candidate);

    }
}
