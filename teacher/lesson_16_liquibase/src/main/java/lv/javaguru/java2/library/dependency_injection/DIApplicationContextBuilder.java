package lv.javaguru.java2.library.dependency_injection;

import java.util.List;

public class DIApplicationContextBuilder {

	private ClassFinder classFinder = new ClassFinder();
	private DIComponentFilter componentFilter = new DIComponentFilter();
	private DIComponentCreator componentsCreator = new DIComponentCreator();
	private DIDependencyResolver dependencyResolver = new DIDependencyResolver();

	public ApplicationContext build(String packageName) {
		try {
			List<Class> allPackageClasses = classFinder.findClassesInsidePackage(packageName);
			List<Class> diComponents = componentFilter.filter(allPackageClasses);

			ApplicationContext applicationContext = new ApplicationContext();
			componentsCreator.create(applicationContext, diComponents);
			dependencyResolver.resolve(applicationContext, diComponents);

			return applicationContext;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

}