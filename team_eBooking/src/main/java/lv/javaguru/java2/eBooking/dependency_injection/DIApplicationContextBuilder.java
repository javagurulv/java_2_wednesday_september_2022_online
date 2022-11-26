package lv.javaguru.java2.eBooking.dependency_injection;

import java.util.List;
import java.util.Set;

public class DIApplicationContextBuilder {

    private ClassFinder classFinder = new ClassFinder();
    private DIComponentFilter diComponentFilter = new DIComponentFilter();
    private DiComponentCreator diComponentCreator = new DiComponentCreator();
    private DIDependencyResolver diDependencyResolver = new DIDependencyResolver();


    public ApplicationContext build(String packageName) {

        try {
            Set<Class> allPackageClasses = classFinder.findAllClassesInsidePackageUsingReflectionLibrary(packageName);
            List<Class> diComponent = diComponentFilter.filter(allPackageClasses);

            ApplicationContext applicationContext = new ApplicationContext();
            diComponentCreator.create(applicationContext, diComponent);
            diDependencyResolver.resolve(applicationContext, diComponent);

            return applicationContext;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
