package lv.javaguru.java2.library.dependency_injection;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.List;

import org.junit.Test;

public class ClassFinderTest {

	@Test
	public void test() throws IOException, ClassNotFoundException {
		ClassFinder finder = new ClassFinder();
		List<Class> classes = finder.findClassesInsidePackage("lv.javaguru.java2.library");
		classes.forEach(aClass -> {
			System.out.println(aClass.getName());
		});
	}

}