package apartment.core.request;

import java.io.LineNumberReader;

public class SearchApartmentRequest {

    private String address;

    private Long roomsAmount;

    private Long floor;

    private Long area;

    public SearchApartmentRequest(String address, Long roomsAmount, Long floor, Long area){
        this.address = address;
        this.roomsAmount = roomsAmount;
        this.floor = floor;
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public Long getRoomsAmount() {
        return roomsAmount;
    }

    public Long getFloor() {
        return floor;
    }

    public Long getArea() {
        return area;
    }

    public boolean isAddressProvided(){
        return this.address != null && !this.address.isBlank();
    }
    public boolean isRoomsAmountProvided(){
        return this.roomsAmount != null;
    }
    public boolean isFloorProvided(){
        return this.floor != null;
    }
    public boolean isAreaProvided(){
        return this.area != null;
    }


}
