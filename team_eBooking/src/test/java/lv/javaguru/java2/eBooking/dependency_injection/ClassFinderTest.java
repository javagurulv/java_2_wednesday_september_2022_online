package lv.javaguru.java2.eBooking.dependency_injection;

import lv.javaguru.java2.eBooking.core.database.Database;
import org.junit.Test;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class ClassFinderTest {

    @Test
    public void shouldFindAllClassesInPackageUsingClassLoader(){
        ClassFinder classFinder = new ClassFinder();

        Set<Class> classList = classFinder.
                findAllClassesInsidePackageUsingReflectionLibrary(
                        "lv.javaguru.java2.eBooking");
        assertEquals(classList.size(),72);
        assertTrue(classList
                .stream()
                .anyMatch(aClass -> aClass.equals(Database.class)));
    }
}