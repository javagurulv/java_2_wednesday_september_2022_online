package lv.javaguru.java2.library.dependency_injection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class DIComponentCreator {

	public void create(ApplicationContext applicationContext,
					   List<Class> diComponents) {
		diComponents.forEach(diComponent -> {
			Optional<Constructor> defaultConstructorOpt = getDefaultConstructor(diComponent);
			if (defaultConstructorOpt.isPresent()) {
				Object diComponentInstance = createInstanceUsingDefaultConstructor(defaultConstructorOpt.get());
				applicationContext.addBean(diComponent, diComponentInstance);
			} else {
				throw new RuntimeException("Class do not have default constructor!");
			}
		});
	}

	private Optional<Constructor> getDefaultConstructor(Class diComponent) {
		return Arrays.stream(diComponent.getConstructors())
				.filter(constructor -> constructor.getParameterCount() == 0)
				.findFirst();
	}

	private Object createInstanceUsingDefaultConstructor(Constructor defaultConstructor) {
		try {
			return defaultConstructor.newInstance();
		} catch (InstantiationException e) {
			throw new RuntimeException(e);
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		} catch (InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}

}
