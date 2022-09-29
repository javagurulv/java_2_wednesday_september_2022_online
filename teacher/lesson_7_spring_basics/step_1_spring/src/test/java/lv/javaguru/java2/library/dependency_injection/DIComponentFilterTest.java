package lv.javaguru.java2.library.dependency_injection;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class DIComponentFilterTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		ClassFinder classFinder = new ClassFinder();
		DIComponentFilter filter = new DIComponentFilter();
		List<Class> classes = classFinder.findClassesInsidePackage("lv.javaguru.java2.library");
		List<Class> diComponents = filter.filter(classes);
		diComponents.forEach(aClass -> {
			System.out.println(aClass.getName());
		});
	}

}