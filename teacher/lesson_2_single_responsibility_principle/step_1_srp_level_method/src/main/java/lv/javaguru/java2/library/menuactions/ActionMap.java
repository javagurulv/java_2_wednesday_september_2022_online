package lv.javaguru.java2.library.menuactions;

import java.util.HashMap;
import java.util.Map;

public class ActionMap {

	private Map<Integer, Action> actionMap;

	public ActionMap() {
		this.actionMap = new HashMap<>();
		actionMap.put(1, new AddNewBookAction());
		actionMap.put(2, new RemoveBookAction());
		actionMap.put(3, new PrintAllBooksAction());
		actionMap.put(4, new ExitProgramAction());
	}

	public Action getAction(int userChoice) {
		return actionMap.get(userChoice);
	}

}
