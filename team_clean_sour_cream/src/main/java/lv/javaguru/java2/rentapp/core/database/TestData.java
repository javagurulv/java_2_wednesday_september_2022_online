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
        return new ArrayList<>(List.of(
                new PassengerCar("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true),
                new PassengerCar("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true),
                new PassengerCar("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true),
                new PassengerCar("d", "d1", true, 1993, Colour.RED, 25.00, EngineType.GAS, "dd1", TransmissionType.MANUAL, 3, 3, 3, true),
                new PassengerCar("e", "e1", true, 1994, Colour.RED, 30.00, EngineType.GAS, "ee1", TransmissionType.MANUAL, 4, 4, 4, true),
                new PassengerCar("f", "f1", true, 1995, Colour.RED, 40.00, EngineType.GAS, "ff1", TransmissionType.AUTOMATIC, 4, 4, 4, false),
                new PassengerCar("g", "g1", true, 1996, Colour.RED, 50.00, EngineType.GAS, "gg1", TransmissionType.AUTOMATIC, 5, 5, 5, false),
                new PassengerCar("h", "h1", true, 1997, Colour.RED, 60.00, EngineType.GAS, "hh1", TransmissionType.AUTOMATIC, 5, 5, 5, false),
                new PassengerCar("i", "i1", true, 1998, Colour.RED, 70.00, EngineType.GAS, "ii1", TransmissionType.AUTOMATIC, 6, 6, 6, false),
                new PassengerCar("j", "j1", true, 1999, Colour.RED, 80.00, EngineType.GAS, "jj1", TransmissionType.AUTOMATIC, 6, 6, 6, false),

                new MiniBus("a", "a1", true, 1990, Colour.RED, 10.00, EngineType.GAS, "aa1", TransmissionType.MANUAL, 2, 2, 2, true),
                new MiniBus("b", "b1", true, 1991, Colour.RED, 15.00, EngineType.GAS, "bb1", TransmissionType.MANUAL, 2, 2, 2, true),
                new MiniBus("c", "c1", true, 1992, Colour.RED, 20.00, EngineType.GAS, "cc1", TransmissionType.MANUAL, 3, 3, 3, true),
                new MiniBus("d", "d1", true, 1993, Colour.RED, 25.00, EngineType.GAS, "dd1", TransmissionType.MANUAL, 3, 3, 3, true),
                new MiniBus("e", "e1", true, 1994, Colour.RED, 30.00, EngineType.GAS, "ee1", TransmissionType.MANUAL, 4, 4, 4, true),
                new MiniBus("f", "f1", true, 1995, Colour.RED, 40.00, EngineType.GAS, "ff1", TransmissionType.AUTOMATIC, 4, 4, 4, false),
                new MiniBus("g", "g1", true, 1996, Colour.RED, 50.00, EngineType.GAS, "gg1", TransmissionType.AUTOMATIC, 5, 5, 5, false),
                new MiniBus("h", "h1", true, 1997, Colour.RED, 60.00, EngineType.GAS, "hh1", TransmissionType.AUTOMATIC, 5, 5, 5, false),
                new MiniBus("i", "i1", true, 1998, Colour.RED, 70.00, EngineType.GAS, "ii1", TransmissionType.AUTOMATIC, 6, 6, 6, false),
                new MiniBus("j", "j1", true, 1999, Colour.RED, 80.00, EngineType.GAS, "jj1", TransmissionType.AUTOMATIC, 6, 6, 6, false)
        ));
    }
}
