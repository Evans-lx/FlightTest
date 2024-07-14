//this  whole code is written by dong zhenpeng

import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.*;

public class TicketTest {

    // 创建一个简单的 Flight 对象
    private Flight createMockFlight() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 100, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-12-12 10:00:00");
        Timestamp dateTo = Timestamp.valueOf("2024-12-12 14:00:00");
        return new Flight(1, "Sydney", "Melbourne", "FL123", "Qantas", dateFrom, dateTo, airplane);
    }

    // 创建一个简单的 Passenger 对象
    private Passenger createMockPassenger() {
        return new Passenger("Jane", "Doe", 25, "Woman", "jane.doe@example.com", "0456789123", "A12345678", "1234567812345678", 123);
    }

    @Test
    public void testTicketCreation() {
        Flight flight = createMockFlight();
        Passenger passenger = createMockPassenger();
        Ticket ticket = new Ticket(1, 1000, flight, true, passenger);
        assertNotNull(ticket);
    }

    @Test
    public void testTicketFields() {
        Flight flight = createMockFlight();
        Passenger passenger = createMockPassenger();
        Ticket ticket = new Ticket(1, 1000, flight, true, passenger);

        assertEquals(1, ticket.getTicket_id());
        assertEquals(1120, ticket.getPrice()); // 1000 * 1.12
        assertEquals(flight, ticket.getFlight());
        assertTrue(ticket.getClassVip());
        assertFalse(ticket.ticketStatus());
        assertEquals(passenger, ticket.getPassenger());
    }

    @Test
    public void testSetTicketStatus() {
        Flight flight = createMockFlight();
        Passenger passenger = createMockPassenger();
        Ticket ticket = new Ticket(1, 1000, flight, true, passenger);

        ticket.setTicketStatus(true);
        assertTrue(ticket.ticketStatus());

        ticket.setTicketStatus(false);
        assertFalse(ticket.ticketStatus());
    }

    @Test
    public void testSetPriceWithServiceTax() {
        Flight flight = createMockFlight();
        Passenger passenger = createMockPassenger();
        Ticket ticket = new Ticket(1, 1000, flight, true, passenger);

        ticket.setPrice(1000);
        assertEquals(1120, ticket.getPrice()); // 1000 * 1.12
    }

    @Test
    public void testSaleByAgeUnder15() {
        Flight flight = createMockFlight();
        Passenger passenger = new Passenger("Jane", "Doe", 14, "Woman", "jane.doe@example.com", "0456789123", "A12345678", "1234567812345678", 123);
        Ticket ticket = new Ticket(1, 1000, flight, true, passenger);

        ticket.setPrice(1000);
        assertEquals(560, ticket.getPrice()); // 1000 * 0.5 = 500, 500 * 1.12 = 560
    }

    @Test
    public void testSaleByAge60AndOver() {
        Flight flight = createMockFlight();
        Passenger passenger = new Passenger("Jane", "Doe", 60, "Woman", "jane.doe@example.com", "0456789123", "A12345678", "1234567812345678", 123);
        Ticket ticket = new Ticket(1, 1000, flight, true, passenger);

        ticket.setPrice(1000);
        assertEquals(0, ticket.getPrice()); // 100% discount for age 60 and over
    }

    @Test
    public void testSetTicketId() {
        Ticket ticket = new Ticket();
        ticket.setTicket_id(10);
        assertEquals(10, ticket.getTicket_id());
    }

    @Test
    public void testSetClassVip() {
        Ticket ticket = new Ticket();
        ticket.setClassVip(true);
        assertTrue(ticket.getClassVip());

        ticket.setClassVip(false);
        assertFalse(ticket.getClassVip());
    }

    @Test
    public void testSetFlight() {
        Flight flight = createMockFlight();
        Ticket ticket = new Ticket();
        ticket.setFlight(flight);
        assertEquals(flight, ticket.getFlight());
    }

    @Test
    public void testSetPassenger() {
        Passenger passenger = createMockPassenger();
        Ticket ticket = new Ticket();
        ticket.setPassenger(passenger);
        assertEquals(passenger, ticket.getPassenger());
    }

    @Test
    public void testInvalidPrice() {
        Flight flight = createMockFlight();
        Passenger passenger = createMockPassenger();
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Ticket(1, -1000, flight, true, passenger);
        });
        assertEquals("Price must be a positive integer", exception.getMessage());
    }

    @Test
    public void testInvalidServiceTax() throws NoSuchFieldException, IllegalAccessException {
        Flight flight = createMockFlight();
        Passenger passenger = createMockPassenger();
        Ticket ticket = new Ticket(1, 1, flight, true, passenger); // Use a valid price

        // Use reflection to set the price to zero
        Field priceField = Ticket.class.getDeclaredField("price");
        priceField.setAccessible(true);
        priceField.set(ticket, 0);

        ticket.serviceTax();
        assertEquals(0, ticket.getPrice());
    }

    @Test
    public void testToString() {
        Flight flight = createMockFlight();
        Passenger passenger = createMockPassenger();
        Ticket ticket = new Ticket(1, 1000, flight, true, passenger);
        ticket.setTicketStatus(true);

        String toStringOutput = ticket.toString();

        // 使用正则表达式验证关心的部分
        assertTrue(toStringOutput.matches("(?s).*Price=1120KZT,.*"));
        assertTrue(toStringOutput.matches("(?s).*Vip status=true.*"));
        assertTrue(toStringOutput.matches("(?s).*Passenger\\{ Fullname= Jane Doe ,email='jane.doe@example.com', phoneNumber='0456789123', passport='A12345678'\\}.*"));
        assertTrue(toStringOutput.matches("(?s).*Ticket was purchased=true.*"));
    }
}
