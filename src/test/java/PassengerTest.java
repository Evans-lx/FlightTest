
//this  whole code is written by dong zhenpeng
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class PassengerTest {

    @Test
    public void testPassengerCreation() {
        Passenger passenger = new Passenger("Jane", "Doe", 25, "Woman", "jane.doe@example.com", "0456789123", "A12345678", "1234567812345678", 123);
        assertNotNull(passenger);
    }

    @Test
    public void testPassengerFields() {
        Passenger passenger = new Passenger("Jane", "Doe", 25, "Woman", "jane.doe@example.com", "0456789123", "A12345678", "1234567812345678", 123);
        assertEquals("Jane", passenger.getFirstName());
        assertEquals("Doe", passenger.getSecondName());
        assertEquals(25, passenger.getAge());
        assertEquals("Woman", passenger.getGender());
        assertEquals("jane.doe@example.com", passenger.getEmail());
        assertEquals("0456789123", passenger.getPhoneNumber());
        assertEquals("A12345678", passenger.getPassport());
        assertEquals("1234567812345678", passenger.getCardNumber());
        assertEquals(123, passenger.getSecurityCode());
    }

    @Test
    public void testSetEmail() {
        Passenger passenger = new Passenger();
        passenger.setEmail("jane.doe@example.com");
        assertEquals("jane.doe@example.com", passenger.getEmail());
    }

    @Test
    public void testSetPhoneNumber() {
        Passenger passenger = new Passenger();
        passenger.setPhoneNumber("0456789123");
        assertEquals("0456789123", passenger.getPhoneNumber());
    }

    @Test
    public void testSetPassport() {
        Passenger passenger = new Passenger();
        passenger.setPassport("A12345678");
        assertEquals("A12345678", passenger.getPassport());
    }

    @Test
    public void testSetCardNumber() {
        Passenger passenger = new Passenger();
        passenger.setCardNumber("1234567812345678");
        assertEquals("1234567812345678", passenger.getCardNumber());
    }

    @Test
    public void testSetSecurityCode() {
        Passenger passenger = new Passenger();
        passenger.setSecurityCode(123);
        assertEquals(123, passenger.getSecurityCode());
    }

    @Test
    public void testInvalidEmail() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Jane", "Doe", 25, "Woman", "invalid-email", "0456789123", "A12345678", "1234567812345678", 123);
        });
        assertEquals("Invalid email address", exception.getMessage());
    }

    @Test
    public void testInvalidPhoneNumber() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Jane", "Doe", 25, "Woman", "jane.doe@example.com", "123456", "A12345678", "1234567812345678", 123);
        });
        assertEquals("Invalid phone number", exception.getMessage());
    }

    @Test
    public void testInvalidPassport() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Jane", "Doe", 25, "Woman", "jane.doe@example.com", "0456789123", "A1234567890", "1234567812345678", 123);
        });
        assertEquals("Invalid passport number", exception.getMessage());
    }

    @Test
    public void testInvalidSecurityCode() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Passenger("Jane", "Doe", 25, "Woman", "jane.doe@example.com", "0456789123", "A12345678", "1234567812345678", -1);
        });
        assertEquals("Invalid security code", exception.getMessage());
    }
}
