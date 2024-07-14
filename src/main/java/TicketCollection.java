//this  whole code is written by dong zhenpeng
import java.util.ArrayList;

public class TicketCollection {

	public static ArrayList<Ticket> tickets = new ArrayList<>();

	public static ArrayList<Ticket> getTickets() {
		return tickets;
	}

	public static void addTickets(ArrayList<Ticket> tickets_db) {
		for (Ticket ticket : tickets_db) {
			validateTicket(ticket);
			TicketCollection.tickets.add(ticket);
		}
	}

	public static void getAllTickets() {
		// Display all available tickets from the Ticket collection
		for (Ticket ticket : tickets) {
			System.out.println(ticket.toString());
		}
	}

	public static Ticket getTicketInfo(int ticket_id) {
		for (Ticket ticket : tickets) {
			if (ticket.getTicket_id() == ticket_id) {
				return ticket;
			}
		}
		return null;
	}

	private static void validateTicket(Ticket ticket) {
		if (ticket.getPrice() <= 0) {
			throw new IllegalArgumentException("Ticket is not valid");
		}
		// Add more validation logic as needed
	}
}
