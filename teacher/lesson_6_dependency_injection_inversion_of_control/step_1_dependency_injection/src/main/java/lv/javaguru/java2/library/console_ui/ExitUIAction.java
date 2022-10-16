package lv.javaguru.java2.library.console_ui;

import lv.javaguru.java2.library.dependency_injection.DIComponent;

@DIComponent
public class ExitUIAction implements UIAction {
	@Override
	public void execute() {
		System.out.println("Good by!");
		System.exit(0);
	}
}
