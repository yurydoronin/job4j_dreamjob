package ru.job4j.spring.police.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.spring.police.model.Accident;

/**
 * Class AccidentRepository.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 19.04.2020
 */
@Repository
public interface AccidentRepository extends CrudRepository<Accident, Integer> {

}
