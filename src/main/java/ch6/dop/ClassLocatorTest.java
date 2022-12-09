package ch6.dop;

import org.junit.jupiter.api.Test;

import java.util.Set;

class ClassLocatorTest {

    @Test
    void getAllClasses() {
        ClassLocator cl = new ClassLocator();
        Set<Class<?>> set = cl.findAllClasses();
    }
}