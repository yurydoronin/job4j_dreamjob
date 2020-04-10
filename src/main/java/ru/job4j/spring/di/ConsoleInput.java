package ru.job4j.spring.di;

import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
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

    public String ask() {
        System.out.print("Enter a name: ");
        String answer = "";
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            answer = reader.readLine();
        } catch (IOException exp) {
            System.out.println(exp.getMessage());
        }
        return answer;
    }
}
