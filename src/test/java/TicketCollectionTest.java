
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.sql.Timestamp;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class TicketCollectionTest {

    @BeforeEach
    public void setUp() {
        TicketCollection.tickets = new ArrayList<>();
    }

    private Flight createMockFlight() {
        Airplane airplane = new Airplane(1, "Boeing 737", 20, 100, 10);
        Timestamp dateFrom = Timestamp.valueOf("2024-12-12 10:00:00");
        Timestamp dateTo = Timestamp.valueOf("2024-12-12 14:00:00");
        return new Flight(1, "Sydney", "Melbourne", "FL123", "Qantas", dateFrom, dateTo, airplane);
    }

    private Passenger createMockPassenger() {
        return new Passenger("Jane", "Doe", 25, "Woman", "jane.doe@example.com", "0456789123", "A12345678", "1234567812345678", 123);
    }

    private Ticket createMockTicket(int id, int price) {
        Flight flight = createMockFlight();
        Passenger passenger = createMockPassenger();
        return new Ticket(id, price, flight, true, passenger);
    }

    @Test
    public void testGetTickets() {
        ArrayList<Ticket> mockTickets = new ArrayList<>();
        mockTickets.add(createMockTicket(1, 1000));
        mockTickets.add(createMockTicket(2, 2000));
        TicketCollection.addTickets(mockTickets);

        ArrayList<Ticket> tickets = TicketCollection.getTickets();
        assertNotNull(tickets);
        assertEquals(2, tickets.size());
    }

    @Test
    public void testAddTickets() {
        ArrayList<Ticket> mockTickets = new ArrayList<>();
        mockTickets.add(createMockTicket(1, 1000));
        TicketCollection.addTickets(mockTickets);

        ArrayList<Ticket> tickets = TicketCollection.getTickets();
        assertNotNull(tickets);
        assertEquals(1, tickets.size());
        assertEquals(1, tickets.get(0).getTicket_id());
    }

    @Test
    public void testAddInvalidTicket() throws NoSuchFieldException, IllegalAccessException {
        ArrayList<Ticket> mockTickets = new ArrayList<>();
        Ticket invalidTicket = createMockTicket(1, 1000); // Create a valid ticket first

        // Use reflection to set the price to an invalid value
        Field priceField = Ticket.class.getDeclaredField("price");
        priceField.setAccessible(true);
        priceField.set(invalidTicket, -1000);

        mockTickets.add(invalidTicket);

        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            TicketCollection.addTickets(mockTickets);
        });

        assertEquals("Ticket is not valid", exception.getMessage());
    }

    @Test
    public void testGetTicketInfo() {
        ArrayList<Ticket> mockTickets = new ArrayList<>();
        mockTickets.add(createMockTicket(1, 1000));
        mockTickets.add(createMockTicket(2, 2000));
        TicketCollection.addTickets(mockTickets);

        Ticket ticket = TicketCollection.getTicketInfo(1);
        assertNotNull(ticket);
        assertEquals(1, ticket.getTicket_id());

        Ticket ticketNotFound = TicketCollection.getTicketInfo(3);
        assertNull(ticketNotFound);
    }
}
