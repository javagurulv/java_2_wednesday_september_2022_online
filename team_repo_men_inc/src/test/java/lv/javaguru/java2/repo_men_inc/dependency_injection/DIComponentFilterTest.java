package lv.javaguru.java2.repo_men_inc.dependency_injection;

import org.junit.Test;

import java.util.List;

public class DIComponentFilterTest {

    @Test
    public void testFilter() {
        ClassFinder finder = new ClassFinder();
        DIComponentFilter diComponentFilter = new DIComponentFilter();
        List<Class> classes = finder.findClassesInsidePackage("lv.javaguru.java2.repo_men_inc");
        List<Class> filteredClasses = diComponentFilter.filter(classes);
        filteredClasses.forEach(aClass -> System.out.println(aClass.getName()));
    }
}