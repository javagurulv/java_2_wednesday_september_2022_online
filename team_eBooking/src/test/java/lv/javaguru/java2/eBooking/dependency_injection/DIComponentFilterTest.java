package lv.javaguru.java2.eBooking.dependency_injection;

import org.junit.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class DIComponentFilterTest {

    @Test
    public void shouldFilterPresentAnnotation() {
        ClassFinder classFinder = new ClassFinder();
        DIComponentFilter component = new DIComponentFilter();

        Set<Class> classes = classFinder.findAllClassesInsidePackageUsingReflectionLibrary(
                "lv.javaguru.java2.eBooking");
        Set<Class> diComponents = component.filter(classes);

        assertEquals(diComponents.size(),1);
    }
}