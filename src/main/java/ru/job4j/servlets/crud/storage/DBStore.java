package ru.job4j.servlets.crud.storage;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.servlets.crud.User;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Properties;

/**
 * Class DBStore.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 16.04.2020
 */
public class DBStore implements Store, AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(DBStore.class);

    private final BasicDataSource pool = new BasicDataSource();

    private static final DBStore INSTANCE = new DBStore();

    private DBStore() {
        this.init();
    }

    public static DBStore getInstance() {
        return INSTANCE;
    }

    private void init() {
        try (InputStream in = DBStore.class.getClassLoader().getResourceAsStream("db.properties")) {
            Properties config = new Properties();
            config.load(Objects.requireNonNull(in));
            pool.setUrl(config.getProperty("url"));
            pool.setUsername(config.getProperty("username"));
            pool.setPassword(config.getProperty("password"));
            pool.setMinIdle(5);
            pool.setMaxIdle(10);
            pool.setMaxOpenPreparedStatements(100);
            pool.setAutoCommitOnReturn(false);
        } catch (Exception e) {
            LOG.info(e.getMessage(), e);
        }
    }

    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    /**
     *
     * @param user, .
     * @return .
     * @throws SQLException .
     */
    @Override
    public boolean add(User user) throws SQLException {
        boolean result = false;
        try (PreparedStatement pst = this.getConnection().prepareStatement(
                "insert into users (id, name) values (?, ?)")) {
            pst.setInt(1, user.getId());
            pst.setString(2, user.getName());
            if (pst.executeUpdate() > 0) {
                result = true;
                this.getConnection().commit();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            this.getConnection().rollback();
        }
        return result;
    }

    /**
     *
     * @param user .
     * @return .
     */
    @Override
    public boolean update(User user) throws SQLException {
        boolean result = false;
        try (PreparedStatement pst = this.getConnection().prepareStatement("update users set name = ? where id = ?")) {
            pst.setString(1, user.getName());
            pst.setInt(2, user.getId());
            if (pst.executeUpdate() > 0) {
                result = true;
                this.getConnection().commit();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            this.getConnection().rollback();
        }
        return result;
    }

    /**
     *
     * @param user, .
     * @return .
     * @throws SQLException .
     */
    @Override
    public boolean delete(User user) throws SQLException {
        boolean result = false;
        try (PreparedStatement pst = this.getConnection().prepareStatement(
                "delete from users where id = ?")) {
            pst.setInt(1, user.getId());
            if (pst.executeUpdate() > 0) {
                result = true;
                this.getConnection().commit();
            }
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            this.getConnection().rollback();
        }
        return result;
    }

    /**
     *
     * @return .
     */
    @Override
    public List<User> findAll() throws SQLException {
        List<User> result = new ArrayList<>();
        try (PreparedStatement pst = this.getConnection().prepareStatement(
                "select id, name, create_date from users")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result.add(this.usersDB(rs));
            }
            this.getConnection().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            this.getConnection().rollback();
        }
        result.forEach(System.out::println);
        return result;
    }

    /**
     *
     * @param rs, .
     * @return .
     * @throws SQLException .
     */
    private User usersDB(ResultSet rs) throws SQLException {
        User result = new User(
                rs.getInt("id"),
                rs.getString("name"));
        result.setCreateDate((rs.getTimestamp("create_date")).toLocalDateTime());
        return result;
    }

    /**
     *
     * @param id, .
     * @return .
     * @throws SQLException .
     */
    @Override
    public User findById(int id) throws SQLException {
        User result = null;
        try (PreparedStatement pst = this.getConnection().prepareStatement(
                "select * from users where id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = this.usersDB(rs);
            }
            this.getConnection().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            this.getConnection().rollback();
        }
        System.out.println(result);
        return result;
    }

    @Override
    public void close() throws Exception {
        if (!this.pool.isClosed()) {
            this.pool.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        DBStore store = DBStore.getInstance();
        User user1 = new User(1, "Вася");
        User user2 = new User(2, "Коля");
        store.add(user1);
        store.add(user2);
        store.findAll();
        System.out.println("");
        store.findById(1);
        System.out.println("");
        User user3 = new User(1, "Маша");
        store.update(user3);
        store.findAll();
        System.out.println("");
        store.delete(user2);
        store.findAll();
    }
}