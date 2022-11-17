package apartment.core.request;

public class AddApartmentRequest {

    private String address;

    private Long roomsAmount;

    private Long floor;

    private Long area;

    public AddApartmentRequest(String address, Long roomsAmount, Long floor, Long area){
        this.address = address;
        this.roomsAmount = roomsAmount;
        this.floor = floor;
        this.area = area;
    }

    public String getAddress(){
        return address;
    }

    public Long getRoomsAmount(){
        return roomsAmount;
    }

    public Long getFloor(){
        return floor;
    }

    public Long getArea(){
        return area;
    }

}
