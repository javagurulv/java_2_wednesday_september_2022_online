package lv.javaguru.java2.eBooking.dependency_injection;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
@DIComponent
@SuppressWarnings("unchecked")
public class DIComponentFilter {
    public List<Class> filter(Set<Class> classes){
        return  classes.stream()
                .filter(aClass -> aClass.isAnnotationPresent(DIComponent.class))
                .collect(Collectors.toList());
    }
}
