package ru.job4j.spring.police.repository;

import org.apache.commons.dbcp2.BasicDataSource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.job4j.spring.police.model.Accident;

import java.io.FileInputStream;
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
 * Class AccidentRepository.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 19.04.2020
 */
public class AccidentRepository implements AutoCloseable {

    private static final Logger LOG = LoggerFactory.getLogger(AccidentRepository.class);

    private final BasicDataSource pool = new BasicDataSource();

    private static final AccidentRepository INSTANCE = new AccidentRepository();

    private AccidentRepository() {
        this.init();
    }

    public static AccidentRepository instOf() {
        return INSTANCE;
    }

    private void init() {
        try (InputStream in = new FileInputStream("db.properties")) {
            Properties config = new Properties();
            config.load(Objects.requireNonNull(in));
            Class.forName(config.getProperty("driver"));
            pool.setUrl(config.getProperty("url"));
            pool.setUsername(config.getProperty("username"));
            pool.setPassword(config.getProperty("password"));
            pool.setMinIdle(5);
            pool.setMaxIdle(10);
            pool.setMaxOpenPreparedStatements(100);
            pool.setDefaultAutoCommit(false);
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
    }

    public Connection getConnection() throws SQLException {
        return pool.getConnection();
    }

    /**
     *
     * @param accident, .
     * @return .
     * @throws SQLException .
     */
    public boolean add(Accident accident) throws SQLException {
        boolean result = false;
        try (PreparedStatement pst = this.getConnection().prepareStatement(
                "insert into accidents (id, name, text, address) values (?, ?, ?, ?)")) {
            pst.setInt(1, accident.getId());
            pst.setString(2, accident.getName());
            pst.setString(3, accident.getText());
            pst.setString(4, accident.getAddress());
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
     * @param accident .
     * @return .
     */
    public boolean update(Accident accident) throws SQLException {
        boolean result = false;
        try (PreparedStatement pst = this.getConnection().prepareStatement(
                "update accidents set name = ?, text = ? where id = ?")) {
            pst.setString(1, accident.getName());
            pst.setString(2, accident.getText());
            pst.setInt(3, accident.getId());
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
     * @param accident, .
     * @return .
     * @throws SQLException .
     */
    public boolean delete(Accident accident) throws SQLException {
        boolean result = false;
        try (PreparedStatement pst = this.getConnection().prepareStatement(
                "delete from accidents where id = ?")) {
            pst.setInt(1, accident.getId());
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
    public List<Accident> findAll() throws SQLException {
        List<Accident> result = new ArrayList<>();
        try (PreparedStatement pst = this.getConnection().prepareStatement(
                "select * from accidents")) {
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result.add(this.accidentsDB(rs));
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
     * @param id, .
     * @return .
     * @throws SQLException .
     */
    public Accident findById(int id) throws SQLException {
        Accident result = null;
        try (PreparedStatement pst = this.getConnection().prepareStatement(
                "select * from accidents where id = ?")) {
            pst.setInt(1, id);
            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                result = this.accidentsDB(rs);
            }
            this.getConnection().commit();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
            this.getConnection().rollback();
        }
        System.out.println(result);
        return result;
    }

    /**
     *
     * @param rs, .
     * @return .
     * @throws SQLException .
     */
    private Accident accidentsDB(ResultSet rs) throws SQLException {
        return new Accident(
                rs.getInt("id"),
                rs.getString("name"),
                rs.getString("text"),
                rs.getString("address"));
    }

    public void close() throws Exception {
        if (!this.pool.isClosed()) {
            this.pool.close();
        }
    }

    public static void main(String[] args) throws SQLException {
        AccidentRepository ar = AccidentRepository.instOf();
        Accident accident = new Accident(1, "имя", "текст", "адрес");
        ar.add(accident);
        ar.findAll();
        System.out.println("");
        ar.findById(1);
    }
}
