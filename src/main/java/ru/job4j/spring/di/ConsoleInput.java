package ru.job4j.spring.di;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Class ConsoleInput.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 10.04.2020
 */
@Component
public class ConsoleInput {

    private static final Logger LOG = LoggerFactory.getLogger(ConsoleInput.class);

    public String ask() {
        System.out.print("Enter a name: ");
        String answer = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            answer = reader.readLine();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e);
        }
        return answer;
    }
}
