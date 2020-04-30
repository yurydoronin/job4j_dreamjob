package ru.job4j.dream.model;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * Class PsqlStore.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 28.04.2020
 */
public class PsqlStore implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(PsqlStore.class);

    private final BasicDataSource pool = new BasicDataSource();

    private static final Store INSTANCE = new PsqlStore();

    private PsqlStore() {
        Properties config = new Properties();
        try (BufferedInputStream buff = new BufferedInputStream(
                new FileInputStream("dreamjobDB.properties"))) {
            config.load(Objects.requireNonNull(buff));
            Class.forName(config.getProperty("driver"));
            pool.setUrl(config.getProperty("url"));
            pool.setUsername(config.getProperty("username"));
            pool.setPassword(config.getProperty("password"));
            pool.setMinIdle(5);
            pool.setMaxIdle(10);
            pool.setMaxOpenPreparedStatements(100);
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    public static Store instOf() {
        return PsqlStore.INSTANCE;
    }

    @Override
    public Collection<Post> findAllPosts() {
        List<Post> posts = new ArrayList<>();
        try (PreparedStatement pst = pool.getConnection().prepareStatement("SELECT * FROM post")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    posts.add(this.post(rs));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return posts;
    }

    @Override
    public Collection<Candidate> findAllCandidates() {
        List<Candidate> posts = new ArrayList<>();
        try (PreparedStatement pst = pool.getConnection().prepareStatement("SELECT * FROM candidate")) {
            try (ResultSet rs = pst.executeQuery()) {
                while (rs.next()) {
                    posts.add(this.candidate(rs));
                }
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return posts;
    }

    @Override
    public void save(Post post) {
        if (post.getId() == 0) {
            create(post);
        } else {
            update(post);
        }
    }

    @Override
    public void save(Candidate candidate) {
        if (candidate.getId() == 0) {
            create(candidate);
        } else {
            update(candidate);
        }
    }

    private Post post(ResultSet rs) throws SQLException {
        return new Post(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("description"));
    }

    private Candidate candidate(ResultSet rs) throws SQLException {
        return new Candidate(
                rs.getInt("id"),
                rs.getString("name"));
    }

    private boolean create(Post post) {
        boolean result = false;
        try (PreparedStatement ps = pool.getConnection().prepareStatement(
                "insert into post(name, description) values (?, ?)")) {
            ps.setString(1, post.getName());
            ps.setString(2, post.getDescription());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private boolean create(Candidate candidate) {
        boolean result = false;
        try (PreparedStatement ps = pool.getConnection().prepareStatement(
                "insert into candidate(name) values (?)")) {
            ps.setString(1, candidate.getName());
            if (ps.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private boolean update(Post post) {
        boolean result = false;
        try (PreparedStatement pst = pool.getConnection().prepareStatement(
                "update post set name = ? where id = ?")) {
            pst.setString(1, post.getName());
            pst.setInt(2, post.getId());
            if (pst.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    private boolean update(Candidate candidate) {
        boolean result = false;
        try (PreparedStatement pst = pool.getConnection().prepareStatement(
                "update candidate set name = ? where id = ?")) {
            pst.setString(1, candidate.getName());
            pst.setInt(2, candidate.getId());
            if (pst.executeUpdate() > 0) {
                result = true;
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Post postFindById(int id) {
        Post result = null;
        try (PreparedStatement pst = pool.getConnection().prepareStatement(
                "select * from post where id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = this.post(rs);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public Candidate candidateFindById(int id) {
        Candidate result = null;
        try (PreparedStatement pst = pool.getConnection().prepareStatement(
                "select * from candidate where id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = this.candidate(rs);
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public void close() throws Exception {
        if (!this.pool.isClosed()) {
            this.pool.close();
        }
    }
}
