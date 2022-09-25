package eBooking;

import java.util.Objects;

public class Appointment {

    private Long id;
    private String masterName;
    private String typeOfService;

    public Appointment(String masterName, String typeOfService) {
        this.masterName=masterName;
        this.typeOfService = typeOfService;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMasterName() {
        return masterName;
    }

    public void setMasterName(String masterName) {
        this.masterName = masterName;
    }

    public String getTypeOfService() {
        return typeOfService;
    }

    public void setTypeOfService(String typeOfService) {
        this.typeOfService = typeOfService;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return id.equals(that.id) && masterName.equals(that.masterName) && typeOfService.equals(that.typeOfService);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, masterName, typeOfService);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "id=" + id +
                ", masterName='" + masterName + '\'' +
                ", typeOfService='" + typeOfService + '\'' +
                '}';
    }
}
