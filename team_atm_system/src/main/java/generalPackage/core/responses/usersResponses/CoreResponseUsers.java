package generalPackage.core.responses.usersResponses;

import java.util.List;

abstract class CoreResponseUsers {

    private List<CoreErrorUsers> errors;

    public CoreResponseUsers() {

    }

    public CoreResponseUsers(List<CoreErrorUsers> errors) {
        this.errors = errors;
    }

    public List<CoreErrorUsers> getErrors() {
        return errors;
    }

    public boolean hasErrors (){
        return errors != null && !errors.isEmpty();
    }
}
