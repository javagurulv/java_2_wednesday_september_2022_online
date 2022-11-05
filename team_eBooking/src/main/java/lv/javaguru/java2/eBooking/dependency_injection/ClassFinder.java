package lv.javaguru.java2.eBooking.dependency_injection;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;
import java.util.HashSet;
import java.util.Set;


public class ClassFinder {

    public Set<Class> findAllClassesInsidePackageUsingReflectionLibrary(String packageName) {
        Reflections reflections = new Reflections(packageName,new SubTypesScanner(false));
        return new HashSet<>(reflections.getSubTypesOf(Object.class));
    }
}
