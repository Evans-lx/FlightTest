import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AirplaneTest {
    private Airplane airplane;

    @BeforeEach
    void setUp() {
        airplane = new Airplane(1, "Boeing 747", 50, 200, 10);
    }

    @Test
    @DisplayName("Test whether Airplane ID is correctly set and retrieved")
    void AirplaneIDTest() {
        airplane.setAirplaneID(2);
        assertNotEquals(0, airplane.getAirplaneID(), "Airplane ID should be correctly set and retrieved.");
    }

    @Test
    @DisplayName("Test whether Airplane Model is correctly set and retrieved")
    void AirplaneModelTest() {
        airplane.setAirplaneModel("Boeing 777");
        assertNotNull(airplane.getAirplaneModel(), "Airplane model should be correctly set and retrieved.");
    }

    @Test
    @DisplayName("Test whether Airplane BusinessSitsNumber is correctly set and retrieved")
    void BusinessSitsNumberTest() {
        airplane.setBusinessSitsNumber(301);
        assertTrue(airplane.getBusinessSitsNumber() >= 1 && airplane.getBusinessSitsNumber() <= 300, "Business seats number must be in the range [1, 300].");
    }

    @Test
    @DisplayName("Test whether Airplane EconomySitsNumber is correctly set and retrieved")
    void EconomySitsNumberTest() {
        airplane.setEconomySitsNumber(0);
        assertTrue(airplane.getEconomySitsNumber() >= 1 && airplane.getEconomySitsNumber() <= 300, "Economy seats number must be in the range [1, 300].");
    }

    @Test
    @DisplayName("Test whether Airplane CrewSitsNumber is correctly set and retrieved")
    void CrewSitsNumberTest() {
        airplane.setCrewSitsNumber(2);
        assertTrue(airplane.getCrewSitsNumber() >= 1 && airplane.getCrewSitsNumber() <= 300, "Crew seats number must be in the range [1, 300].");
    }

    @Test
    @DisplayName("Test whether toString returns the correct string representation")
    void testToString() {
        Airplane airplane = new Airplane(1, "Boeing 747", 50, 200, 10);
        String expected = String.format("Airplane{model=%s, business sits=%d, economy sits=%d, crew sits=%d}", "Boeing 747", 50, 200, 10);
        assertEquals(expected, airplane.toString(), "The toString method should return the correct string representation.");
    }

    @Test
    @DisplayName("Test whether getAirPlaneInfo returns the correct airplane information")
    void testGetAirPlaneInfoValidID() {
        Airplane airplane = Airplane.getAirPlaneInfo(1);
        assertNotNull(airplane, "getAirPlaneInfo should not return null for a valid ID.");
        assertEquals("Boeing 747", airplane.getAirplaneModel(), "The model should match the expected value for ID 1.");
        assertEquals(50, airplane.getBusinessSitsNumber(), "The business sits number should match the expected value for ID 1.");
        assertEquals(200, airplane.getEconomySitsNumber(), "The economy sits number should match the expected value for ID 1.");
        assertEquals(10, airplane.getCrewSitsNumber(), "The crew sits number should match the expected value for ID 1.");
    }

    @Test
    @DisplayName("Test whether getAirPlaneInfo returns null for an invalid ID")
    void testGetAirPlaneInfoInvalidID() {
        Airplane airplane = Airplane.getAirPlaneInfo(999); // Assuming 999 is an invalid ID
        assertNull(airplane, "getAirPlaneInfo should return null for an invalid ID.");
    }
}