package myApp;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

public class Appointment {

    private String masterName;
    private String typeOfService;
    private BigDecimal price;
    private Date date;

    public Appointment(String masterName, String typeOfService, Date date) {
        this.masterName = masterName;
        this.typeOfService = typeOfService;
        this.date = date;
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

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Appointment that = (Appointment) o;
        return masterName.equals(that.masterName) && typeOfService.equals(that.typeOfService) && price.equals(that.price) && date.equals(that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(masterName, typeOfService, price, date);
    }

    @Override
    public String toString() {
        return "Appointment{" +
                "masterName='" + masterName + '\'' +
                ", typeOfService='" + typeOfService + '\'' +
                ", price=" + price +
                ", date=" + date +
                '}';
    }
}
