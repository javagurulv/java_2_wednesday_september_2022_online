package lv.javaguru.java2.rentapp.core.requests;

import lombok.*;

@Getter
@Setter
@Builder
public class ShowAllVehiclesRequest {

    private Paging paging;

}
