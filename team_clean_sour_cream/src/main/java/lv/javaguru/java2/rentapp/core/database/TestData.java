package lv.javaguru.java2.rentapp.core.database;

import lv.javaguru.java2.rentapp.domain.MiniBus;
import lv.javaguru.java2.rentapp.domain.PassengerCar;
import lv.javaguru.java2.rentapp.domain.Vehicle;
import lv.javaguru.java2.rentapp.enums.Colour;
import lv.javaguru.java2.rentapp.enums.EngineType;
import lv.javaguru.java2.rentapp.enums.TransmissionType;

import java.util.ArrayList;
import java.util.List;

class TestData {

    public static List<Vehicle> getTestList() {
        PassengerCar passengerCar1 = new PassengerCar("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar1.setId(1L);
        PassengerCar passengerCar2 = new PassengerCar("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true);
        passengerCar2.setId(2L);
        PassengerCar passengerCar3 = new PassengerCar("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true);
        passengerCar3.setId(3L);
        PassengerCar passengerCar4 = new PassengerCar("d", "d1", true, 1993, Colour.RED, 25.00, EngineType.GAS, "dd1", TransmissionType.MANUAL, 3, 3, 3, true);
        passengerCar4.setId(4L);
        PassengerCar passengerCar5 = new PassengerCar("e", "e1", true, 1994, Colour.RED, 30.00, EngineType.GAS, "ee1", TransmissionType.MANUAL, 4, 4, 4, true);
        passengerCar5.setId(5L);
        PassengerCar passengerCar6 = new PassengerCar("f", "f1", true, 1995, Colour.RED, 40.00, EngineType.GAS, "ff1", TransmissionType.AUTOMATIC, 4, 4, 4, false);
        passengerCar6.setId(6L);
        PassengerCar passengerCar7 = new PassengerCar("g", "g1", true, 1996, Colour.RED, 50.00, EngineType.GAS, "gg1", TransmissionType.AUTOMATIC, 5, 5, 5, false);
        passengerCar7.setId(7L);
        PassengerCar passengerCar8 = new PassengerCar("h", "h1", true, 1997, Colour.RED, 60.00, EngineType.GAS, "hh1", TransmissionType.AUTOMATIC, 5, 5, 5, false);
        passengerCar8.setId(8L);
        PassengerCar passengerCar9 = new PassengerCar("i", "i1", true, 1998, Colour.RED, 70.00, EngineType.GAS, "ii1", TransmissionType.AUTOMATIC, 6, 6, 6, false);
        passengerCar9.setId(9L);
        PassengerCar passengerCar10 = new PassengerCar("j", "j1", true, 1999, Colour.RED, 80.00, EngineType.GAS, "jj1", TransmissionType.AUTOMATIC, 6, 6, 6, false);
        passengerCar10.setId(10L);

        MiniBus miniBus1 = new MiniBus("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true);
        miniBus1.setId(11L);
        MiniBus miniBus2 = new MiniBus("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true);
        miniBus2.setId(12L);
        MiniBus miniBus3 = new MiniBus("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true);
        miniBus3.setId(13L);
        MiniBus miniBus4 = new MiniBus("d", "d1", true, 1993, Colour.RED, 25.00, EngineType.GAS, "dd1", TransmissionType.MANUAL, 3, 3, 3, true);
        miniBus4.setId(14L);
        MiniBus miniBus5 = new MiniBus("e", "e1", true, 1994, Colour.RED, 30.00, EngineType.GAS, "ee1", TransmissionType.MANUAL, 4, 4, 4, true);
        miniBus5.setId(15L);
        MiniBus miniBus6 = new MiniBus("f", "f1", true, 1995, Colour.RED, 40.00, EngineType.GAS, "ff1", TransmissionType.AUTOMATIC, 4, 4, 4, false);
        miniBus6.setId(16L);
        MiniBus miniBus7 = new MiniBus("g", "g1", true, 1996, Colour.RED, 50.00, EngineType.GAS, "gg1", TransmissionType.AUTOMATIC, 5, 5, 5, false);
        miniBus7.setId(17L);
        MiniBus miniBus8 = new MiniBus("h", "h1", true, 1997, Colour.RED, 60.00, EngineType.GAS, "hh1", TransmissionType.AUTOMATIC, 5, 5, 5, false);
        miniBus8.setId(18L);
        MiniBus miniBus9 = new MiniBus("i", "i1", true, 1998, Colour.RED, 70.00, EngineType.GAS, "ii1", TransmissionType.AUTOMATIC, 6, 6, 6, false);
        miniBus9.setId(19L);
        MiniBus miniBus10 = new MiniBus("j", "j1", true, 1999, Colour.RED, 80.00, EngineType.GAS, "jj1", TransmissionType.AUTOMATIC, 6, 6, 6, false);
        miniBus10.setId(20L);
        return new ArrayList<>(List.of(
                passengerCar1, passengerCar2, passengerCar3, passengerCar4, passengerCar5, passengerCar6, passengerCar7, passengerCar8, passengerCar9, passengerCar10,
                miniBus1, miniBus2, miniBus3, miniBus4, miniBus5, miniBus6, miniBus7, miniBus8, miniBus9, miniBus10
        ));
    }
}
