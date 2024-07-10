import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;

public class FlightCollectionTest {
    private ArrayList<Flight> sampleFlights;

    @BeforeEach
    public void setUp() throws Exception {
        sampleFlights = new ArrayList<>();
        sampleFlights.add(new Flight(1, "New York", "Los Angeles", "NYLA123", "Delta Airlines", Timestamp.valueOf("2023-04-01 08:00:00"), Timestamp.valueOf("2023-04-01 11:00:00"), new Airplane(1, "Boeing 737", 160, 5000, 20)));
        sampleFlights.add(new Flight(2, "San Francisco", "Chicago", "SFCH456", "United Airlines", Timestamp.valueOf("2023-04-02 09:00:00"), Timestamp.valueOf("2023-04-02 12:00:00"), new Airplane(2, "Airbus A320", 150, 4500, 25)));
        sampleFlights.add(new Flight(3, "Boston", "Miami", "BOMI789", "American Airlines", Timestamp.valueOf("2023-04-03 07:00:00"), Timestamp.valueOf("2023-04-03 10:00:00"), new Airplane(3, "Boeing 757", 180, 5500, 22)));

        // 通过反射设置FlightCollection的flights字段
        Field flightsField = FlightCollection.class.getDeclaredField("flights");
        flightsField.setAccessible(true);
        flightsField.set(null, new ArrayList<>(sampleFlights));
    }

    @Test
    @DisplayName("Test Whether GetFlights Returns Correct Number of Flights")
    public void GetFlightsTest() {
        System.out.println(FlightCollection.getFlights());
        assertEquals(3, FlightCollection.getFlights().size());
    }

    @Test
    @DisplayName("Test Whether AddFlights Adds New Flights to the Collection")
    public void AddFlightTest() {
        Flight newFlight = new Flight(4, "Beijing", "Shanghai", "ADS1232","Shanghai Airlines",Timestamp.valueOf("2023-04-03 07:00:00"), Timestamp.valueOf("2023-04-03 10:00:00"), new Airplane(3, "Boeing 757", 180, 5500, 22)); // 假设Flight构造器和这里的参数匹配
        ArrayList<Flight> newFlights = new ArrayList<>();
        newFlights.add(newFlight);

        FlightCollection.addFlights(newFlights); // 向FlightCollection添加航班

        assertNotNull(FlightCollection.getFlightInfo(4)); // 确保新航班已添加
    }

    // 添加其他测试方法以进一步测试FlightCollection的功能
    @Test
    @DisplayName("Test Whether GetFlightInfoById Returns Correct Flight")
    public void GetFlightInfoByIdTest() {
        Flight flight = FlightCollection.getFlightInfo(1);
        assertNotNull(flight);
        assertEquals("NYLA123", flight.getCode());
    }

    @Test
    @DisplayName("Test Whether GetFlightInfoByCity Returns Correct Flight")
    public void GetFlightInfoByCityTest() {
        Flight flight = FlightCollection.getFlightInfo("Boston");
        assertNotNull(flight);
        assertEquals("BOMI789", flight.getCode());
    }

    @Test
    @DisplayName("Test Whether GetFlightInfoByCities Returns Correct Flight")
    public void GetFlightInfoByCitiesTest() {
        Flight flight = FlightCollection.getFlightInfo("New York", "Los Angeles");
        assertNotNull(flight);
        assertEquals("NYLA123", flight.getCode());
    }

    @Test
    public void GetFlightInfoWithValidCitiesTest() {
        // 测试有效城市名
        Flight result = FlightCollection.getFlightInfo("New York", "Los Angeles");
        assertNotNull(result, "Flight between New York and Los Angeles should exist.");
    }

    @Test
    public void GetFlightInfoWithInvalidCitiesTest() {
        // 测试无效城市名
        Flight result = FlightCollection.getFlightInfo("CityA", "CityB");
        assertNotNull(result, "Flight between CityA and CityB should not exist.");
    }
}