package ru.job4j.spring.di;

import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Context.
 *
 * @author Yury Doronin (doronin.ltd@gmail.com)
 * @version 1.0
 * @since 10.04.2020
 */
public class Context {

    private final Map<String, Object> els = new HashMap<>();

    /**
     * Registering for classes.
     *
     * @param cl a class that will to be used in the project.
     */
    public void reg(Class cl) {
        Constructor[] constructors = cl.getDeclaredConstructors();
        if (constructors.length > 1) {
            throw new IllegalStateException("Class has multiple constructors: " + cl.getCanonicalName());
        }
        Constructor con = constructors[0];
        List<Object> args = new ArrayList<>();
        for (Class arg : con.getParameterTypes()) {
            if (!els.containsKey(arg.getCanonicalName())) {
                throw new IllegalStateException("Object is not found in the context: " + arg.getCanonicalName());
            }
            args.add(els.get(arg.getCanonicalName()));
        }
        try {
            els.put(cl.getCanonicalName(), con.newInstance(args.toArray()));
        } catch (Exception e) {
            throw new IllegalStateException("Cannot create an instance of: " + cl.getCanonicalName(), e);
        }
    }

    /**
     * Getting an instantiated object.
     *
     * @param inst an instantiated class.
     * @param <T> a class type.
     * @return the instantiated object.
     */
    @SuppressWarnings("unchecked")
    public <T> T get(Class<T> inst) {
        return (T) els.get(inst.getCanonicalName());
    }
}
