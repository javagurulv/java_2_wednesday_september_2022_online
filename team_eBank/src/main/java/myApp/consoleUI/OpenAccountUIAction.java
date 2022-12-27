package myApp.consoleUI;

/*
//@Component
public class OpenAccountUIAction implements UIAction {
    @Autowired
    private OpenAccountService service;
    @Autowired
    private UserService userService;

    @Override
    public void execute() {
        String personalCode = userService.getPersonalCode();
        OpenAccountRequest request = new OpenAccountRequest(personalCode);
        OpenAccountResponse response = service.execute(request);
        if (response.isCompleted()) {
            System.out.println("Account has been opened");
        } else {
            System.out.println("Error");
            ;
        }
    }
}

 */