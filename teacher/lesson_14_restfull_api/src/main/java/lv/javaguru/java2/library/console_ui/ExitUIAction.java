package lv.javaguru.java2.library.console_ui;

import org.springframework.stereotype.Component;

@Component
public class ExitUIAction implements UIAction {
	@Override
	public void execute() {
		System.out.println("Good by!");
		System.exit(0);
	}
}
