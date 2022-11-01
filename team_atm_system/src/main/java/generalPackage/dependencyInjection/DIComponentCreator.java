package generalPackage.dependencyInjection;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class DIComponentCreator {

    public void create(ApplicationContext applicationContext,
                       List<Class> diComponents) {
        diComponents.forEach(diComponent -> {
            Optional<Constructor> defaultConstructorOpt = getDefaultConstructor(diComponent);
            if (defaultConstructorOpt.isPresent()) {
                Object diComponentInstance = createInstanceUsingDefaultConstructor(defaultConstructorOpt.get());
                applicationContext.addBean(diComponent, diComponentInstance);
            } else {
                throw new RuntimeException("Class do not have default constructor");
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
        } catch (InstantiationException exception) {
            throw new RuntimeException(exception);
        } catch (IllegalAccessException exception) {
            throw new RuntimeException(exception);
        } catch (InvocationTargetException exception) {
            throw new RuntimeException(exception);
        }
    }

}
