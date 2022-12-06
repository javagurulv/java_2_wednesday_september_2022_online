package lv.javaguru.java2.tasksScheduler.core.services.menu_services;



import lv.javaguru.java2.tasksScheduler.core.database.UsersRepository;
import lv.javaguru.java2.tasksScheduler.core.domain.User;
import lv.javaguru.java2.tasksScheduler.enums.MenuType;
import lv.javaguru.java2.tasksScheduler.core.requests.GetUsersRequest;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Ordering;
import lv.javaguru.java2.tasksScheduler.core.requests.ordering_paging.Paging;
import lv.javaguru.java2.tasksScheduler.core.responses.CoreError;
import lv.javaguru.java2.tasksScheduler.core.responses.GetUsersResponse;
//import lv.javaguru.java2.tasksScheduler.core.validators.services.GetUsersValidator;
import lv.javaguru.java2.tasksScheduler.core.services.validators.GetUsersValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class GetUsersService {

    @Autowired private UsersRepository usersRepository;
    @Autowired private GetUsersValidator validator;

    public GetUsersResponse execute(GetUsersRequest request, MenuType menuType) {
        List<CoreError> errors = validator.validate(request, menuType);
        if (!errors.isEmpty()) {
            return new GetUsersResponse(null, errors);
        }

        List<User> users = filter(request);
        users = order(users, request.getOrdering());
        users = paging(users, request.getPaging());

        return new GetUsersResponse(users, null);
    }

    private List<User> filter(GetUsersRequest request) {
        if (request.isUsernameProvided() && !request.isEmailProvided()) {
            return usersRepository.getUsersByUsername(request.getUsername());
        }
        if (!request.isUsernameProvided() && request.isEmailProvided()) {
            return usersRepository.getUsersByEmail(request.getEmail());
        }
        if (request.isUsernameProvided() && request.isEmailProvided()) {
            return usersRepository.getUsersByUsernameAndEmail(request.getUsername(), request.getEmail());
        }
        return usersRepository.getAllUsers();
    }

    private List<User> order(List<User> users, Ordering ordering) {
        if (ordering != null) {
            Comparator<User> comparator = ordering.getOrderBy().equals("username")
                    ? Comparator.comparing(User::getUsername)
                    : Comparator.comparing(User::getEmail);
            if (ordering.getOrderDirection().equals("DESCENDING")) {
                comparator = comparator.reversed();
            }
            return users.stream().sorted(comparator).collect(Collectors.toList());
        } else {
            return users;
        }
    }

    private List<User> paging(List<User> users, Paging paging) {
        if (paging != null) {
            int skip = (paging.getPageNumber() - 1) * paging.getPageSize();
            return users.stream()
                    .skip(skip)
                    .limit(paging.getPageSize())
                    .collect(Collectors.toList());
        } else {
            return users;
        }
    }
}
