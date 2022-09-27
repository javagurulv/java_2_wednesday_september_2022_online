package lv.javaguru.java2.eBooking;

import java.util.Objects;

public class Client {
    private Long id;
    private String clientEmail;
    private String clientPhoneNumber;

    public Client(String clientEmail, String clientPhoneNumber) {
        this.clientEmail = clientEmail;
        this.clientPhoneNumber = clientPhoneNumber;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhoneNumber() {
        return clientPhoneNumber;
    }

    public void setClientPhoneNumber(String clientPhoneNumber) {
        this.clientPhoneNumber = clientPhoneNumber;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return id.equals(client.id) && clientEmail.equals(client.clientEmail) && clientPhoneNumber.equals(client.clientPhoneNumber);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientEmail, clientPhoneNumber);
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", clientEmail='" + clientEmail + '\'' +
                ", clientPhoneNumber='" + clientPhoneNumber + '\'' +
                '}';
    }
}
