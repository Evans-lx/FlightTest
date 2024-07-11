import java.util.ArrayList;

public class FlightCollection {
	
	public static ArrayList<Flight> flights;

	public static ArrayList<Flight> getFlights() {
		return flights;
	}

    public static void addFlights(ArrayList<Flight> flights) {
		FlightCollection.flights.addAll(flights);
	}
    
	public static Flight getFlightInfo(String city1, String city2) {
        // 显示从city1直飞到city2的航班
        for (Flight flight : flights) {
            if (flight.getDepartTo().equals(city1) && flight.getDepartFrom().equals(city2)) {
                return flight;
            }
        }
        return null;
    }

    public static Flight getFlightInfo(String city) {
        // 选择depart_to为city的航班
        for (Flight flight : flights) {
            if (flight.getDepartTo().equals(city)) {
                return flight;
            }
        }
        return null;
    }

    public static Flight getFlightInfo(int flight_id) {
        // 选择具有特定flight_id的航班
        for (Flight flight : flights) {
            if (flight.getFlightID() == flight_id) {
                return flight;
            }
        }
        return null;
	}
    

}
