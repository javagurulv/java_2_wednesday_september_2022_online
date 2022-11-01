package generalPackage.dependencyInjection;

import java.util.List;

public class DIApplicationContextBuilder {

    private ClassFinder classFinder = new ClassFinder();
    private DIComponentCreator diComponentCreator = new DIComponentCreator();
    private DIComponentFilter diComponentFilter = new DIComponentFilter();
    private DIDependencyResolver diDependencyResolver = new DIDependencyResolver();


    public ApplicationContext build(String packageName) {
        try {
            List<Class> allPackagesClasses = classFinder.findClassesInsidePackage(packageName);
            List<Class> diComponents = diComponentFilter.filter(allPackagesClasses);

            ApplicationContext applicationContext = new ApplicationContext();
            diComponentCreator.create(applicationContext, diComponents);
            diDependencyResolver.resolve(applicationContext, diComponents);

            return applicationContext;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
