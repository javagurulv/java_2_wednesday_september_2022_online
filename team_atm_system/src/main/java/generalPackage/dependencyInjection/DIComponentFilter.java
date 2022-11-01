package generalPackage.dependencyInjection;

import java.util.List;
import java.util.stream.Collectors;

public class DIComponentFilter {

    public List<Class> filter(List<Class> classes) {

        return classes.stream()
                .filter(classesToFilter -> classesToFilter.isAnnotationPresent(DIComponent.class))
                .collect(Collectors.toList());
    }

}
