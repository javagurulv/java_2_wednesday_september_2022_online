//package lv.javaguru.java2.repo_men_inc.dependency_injection;
//
//import lv.javaguru.java2.repo_men_inc.core.database.Database;
//import lv.javaguru.java2.repo_men_inc.core.domain.Debtor;
//
//import java.lang.reflect.Constructor;
//import java.lang.reflect.InvocationTargetException;
//import java.util.Arrays;
//import java.util.List;
//import java.util.Optional;
//
//public class DIComponentCreator {
//
//    public void create(ApplicationContext applicationContext, List<Class> diComponents) {
//        diComponents.forEach(diComponent -> {
//            Optional<Constructor> defaultConstructorOpt = getDefaultConstructor(diComponent);
//            if (defaultConstructorOpt.isPresent()) {
//                Object diComponentInstance = createInstanceUsingDefaultConstructor(defaultConstructorOpt.get());
//                applicationContext.addBean(diComponent, diComponentInstance);
//
//                if (diComponentInstance instanceof Database databaseImpl) {
//                    databaseImpl.save(new Debtor("mr x"));
//                    databaseImpl.save(new Debtor("mr y"));
//                    databaseImpl.save(new Debtor("mr z"));
//                    databaseImpl.getById(1L).getList().add("leg");
//                    databaseImpl.getById(2L).getList().add("arm");
//                    databaseImpl.getById(3L).getList().add("arm");
//                    databaseImpl.getById(3L).getList().add("liver");
//                }
//            } else {
//                throw new RuntimeException("Class do not have default constructor!");
//            }
//        });
//    }
//
//    private Optional<Constructor> getDefaultConstructor(Class diComponent) {
//        return Arrays.stream(diComponent.getConstructors())
//                .filter(constructor -> constructor.getParameterCount() == 0)
//                .findFirst();
//    }
//
//    private Object createInstanceUsingDefaultConstructor(Constructor defaultConstructor) {
//        try {
//            return defaultConstructor.newInstance();
//        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
//            throw new RuntimeException(e);
//        }
//    }
//}
