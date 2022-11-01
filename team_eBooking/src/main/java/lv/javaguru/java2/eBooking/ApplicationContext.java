package lv.javaguru.java2.eBooking;

import lv.javaguru.java2.eBooking.console_ui.*;
import lv.javaguru.java2.eBooking.core.database.Database;
import lv.javaguru.java2.eBooking.core.database.InMemoryDatabase;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentAddService;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentGetAllService;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentRemoveService;
import lv.javaguru.java2.eBooking.core.services.appointment.AppointmentSearchService;
import lv.javaguru.java2.eBooking.core.services.client.ClientAddService;
import lv.javaguru.java2.eBooking.core.services.client.ClientGetAllService;
import lv.javaguru.java2.eBooking.core.services.client.ClientRemoveService;
import lv.javaguru.java2.eBooking.core.services.client.ClientSearchService;
import lv.javaguru.java2.eBooking.core.services.validators.*;

import java.util.HashMap;
import java.util.Map;

public class ApplicationContext {

    private Map<Class, Object> beans = new HashMap<>();

    public ApplicationContext() {
        beans.put(Database.class, new InMemoryDatabase());
        beans.put(ClientAddValidator.class,new ClientAddValidator());
        beans.put(ClientRemoveValidator.class,new ClientRemoveValidator());
        beans.put(ClientSearchValidator.class,new ClientSearchValidator());
        beans.put(AppointmentAddValidator.class, new AppointmentAddValidator());
        beans.put(AppointmentRemoveValidator.class, new AppointmentRemoveValidator());
        beans.put(AppointmentSearchValidator.class, new AppointmentSearchValidator());

        beans.put(ClientAddService.class, new ClientAddService(
                getBean(Database.class),
                getBean(ClientAddValidator.class)));
        beans.put(AppointmentAddService.class, new AppointmentAddService(
                getBean(Database.class),
                getBean(AppointmentAddValidator.class)));
        beans.put(ClientRemoveService.class, new ClientRemoveService(
                getBean(Database.class),
                getBean(ClientRemoveValidator.class)));
        beans.put(AppointmentRemoveService.class, new AppointmentRemoveService(
                getBean(Database.class),
                getBean(AppointmentRemoveValidator.class)));
        beans.put(ClientGetAllService.class,new ClientGetAllService(
                getBean(Database.class)));
        beans.put(AppointmentGetAllService.class, new AppointmentGetAllService(
                getBean(Database.class)));
        beans.put(ClientSearchService.class, new ClientSearchService(
                getBean(Database.class),
                getBean(ClientSearchValidator.class)));
        beans.put(AppointmentSearchService.class, new AppointmentSearchService(
                getBean(Database.class),
                getBean(AppointmentSearchValidator.class)));

        beans.put(AddClientUIAction.class, new AddClientUIAction(
                getBean(ClientAddService.class)));
        beans.put(AddAppointmentUIAction.class, new AddAppointmentUIAction(
                getBean(AppointmentAddService.class)));
        beans.put(SearchClientUIAction.class, new SearchClientUIAction(
                getBean(ClientSearchService.class)));
        beans.put(SearchAppointmentUIAction.class, new SearchAppointmentUIAction(
                getBean(AppointmentSearchService.class)));
        beans.put(RemoveClientUIAction.class, new RemoveClientUIAction(
                getBean(ClientRemoveService.class)));
        beans.put(RemoveAppointmentUIAction.class, new RemoveAppointmentUIAction(
                getBean(AppointmentRemoveService.class)));

        beans.put(PrintApplicationMenuUIAction.class, new PrintApplicationMenuUIAction());
        beans.put(ExitApplicationUIAction.class,new ExitApplicationUIAction());
        beans.put(PrintClientUIAction.class, new PrintClientUIAction(
                getBean(ClientGetAllService.class)));
        beans.put(PrintAppointmentUIAction.class, new PrintAppointmentUIAction(
                getBean(AppointmentGetAllService.class)));

    }
    public <T extends Object> T getBean(Class c) {
        return (T) beans.get(c);
    }
}
