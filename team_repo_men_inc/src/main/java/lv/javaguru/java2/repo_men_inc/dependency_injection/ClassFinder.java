//package lv.javaguru.java2.repo_men_inc.dependency_injection;
//
//import org.reflections.Reflections;
//import org.reflections.scanners.SubTypesScanner;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class ClassFinder {
//
//    public List<Class> findClassesInsidePackage(String packageName) {
//        Reflections reflections = new Reflections(packageName, new SubTypesScanner(false));
//        return new ArrayList<>(reflections.getSubTypesOf(Object.class));
//    }
//
//}
