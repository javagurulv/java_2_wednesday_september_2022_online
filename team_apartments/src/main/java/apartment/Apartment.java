package apartment;

import java.util.Objects;

public class Apartment {

    private Long id;
    private String address;

    private Long roomsAmount;

    private Long floor;

    private Long area;

    public Apartment(String address, Long roomsAmount, Long floor, Long area){
        this.address = address;
        this.roomsAmount = roomsAmount;
        this.floor = floor;
        this.area = area;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAddress() {return address; }
    public void setAddress(String address) {this.address = address; }

    public Long getRoomsAmount() {return roomsAmount; }
    public void setRoomsAmount(Long roomsAmount) {this.roomsAmount = roomsAmount; }

    public Long getFloor() {return floor; }
    public void setFloor(Long floor) {this.floor = floor; }

    public Long getArea() {return  area; }
    public void setArea(Long area) {this.area = area; }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Apartment apartment = (Apartment) o;
        return Objects.equals(address, apartment.address) &&
                Objects.equals(roomsAmount, apartment.roomsAmount) &&
                Objects.equals(floor, apartment.floor) &&
                Objects.equals(area, apartment.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(address, roomsAmount, floor, area);
    }

    @Override
    public String toString() {
        return "Apartment{" +
                "Address='" + address + '\'' +
                ", Rooms amount='" + roomsAmount + '\'' +
                ", Floor='" + floor + '\'' +
                ", Area='" + area + '\'' +
                '}';
    }
}
