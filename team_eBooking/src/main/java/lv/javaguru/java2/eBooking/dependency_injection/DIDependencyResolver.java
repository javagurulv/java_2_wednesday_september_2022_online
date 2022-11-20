package lv.javaguru.java2.eBooking.dependency_injection;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DIDependencyResolver {

    public void resolve(ApplicationContext applicationContext, List<Class> diComponents){
        diComponents.forEach(diComponent-> {
            Object diInstance = applicationContext.getBean(diComponent);
            List<Field> diFields = findFieldWithDIDependencyAnnotation(diComponent);
            diFields.forEach(field -> {
                tryToSetDependency(applicationContext,field,diInstance);
            });
        });
    }


    public void tryToSetDependency(ApplicationContext applicationContext, Field field, Object diInstance){
        Class fieldType = field.getType(); //poluchaem Type polja
        Object fieldInstance = applicationContext.getBean(fieldType);
        if(fieldInstance == null){
            throw new RuntimeException("Dependency not found");
        }else{
            setValueToPrivateField(diInstance,field,fieldInstance);
        }
    }

    public void setValueToPrivateField(Object diInstance,Field field,Object fieldInstance){

        try{
            field.setAccessible(true);
            field.set(diInstance,fieldInstance);

        } catch (IllegalAccessException e) {
            throw new RuntimeException("Cannot resolve dependency!");
        }
    }

    public List<Field> findFieldWithDIDependencyAnnotation (Class diComponent){
        return Arrays.stream(diComponent.getDeclaredFields())
                .filter(field -> field.isAnnotationPresent(DIDependency.class))
                .collect(Collectors.toList());
    }
}
