package myApp.dependency_injection;

import org.junit.Test;

import java.io.IOException;
import java.util.List;


public class ClassFinderTest {

    @Test
    public void test() throws IOException, ClassNotFoundException {
        ClassFinder finder = new ClassFinder();
        List<Class> classes = finder.findClassesInsidePackage("myApp");
        classes.forEach(aClass -> {
            System.out.println(aClass.getName());
        });
    }
}