package ru.job4j.spring.police.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import ru.job4j.spring.police.model.Accident;
import ru.job4j.spring.police.repository.AccidentRepository;

import java.sql.SQLException;
import java.util.List;

/**
 * Class AccidentService.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 19.04.2020
 */
@Component
public class AccidentService {

    private final AccidentRepository ar;

    public AccidentService(AccidentRepository ar) {
        this.ar = ar;
    }

    public boolean add(Accident accident) throws SQLException {
        return this.ar.add(accident);
    }

//    public boolean update(Accident accident) {
//
//    }
//
//    public boolean delete(Accident accident) {
//
//    }
//
//    public List<Accident> findAll() {
//
//    }
//
//    public Accident findById(int id) {
//
//    }

}
