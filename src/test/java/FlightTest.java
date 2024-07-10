import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.opentest4j.AssertionFailedError;

import java.lang.reflect.Field;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import java.util.ArrayList;

class FlightTest {

    @Test
    @DisplayName("Flight Fields Test When Everything is Set")
    void FlightFieldsTest1() throws ParseException {
        // 设置测试环境
        // Hypothetical values for creating an Airplane object
        Airplane airplane1 = new Airplane(1, "Boeing 747", 160, 5000, 20);
        Flight flight1 = new Flight();
        flight1.setFlightID(1);
        flight1.setAirplane(airplane1);
        flight1.setDateTo(new Timestamp(new SimpleDateFormat("HH:mm:ss dd/mm/yy").parse("13:30:45 01/10/23").getTime()));
        flight1.setDateFrom(new Timestamp(new SimpleDateFormat("HH:mm:ss dd/mm/yy").parse("15:30:45 30/09/23").getTime()));
        flight1.setDepartFrom("New York");
        flight1.setDepartTo("London");
        flight1.setCode("NY123");
        flight1.setCompany("AirTest");
        // 验证字段
        assertNotEquals(flight1.getFlightID(),0);
        assertNotNull(flight1.getAirplane());
        assertNotNull(flight1.getDateTo());
        assertNotNull(flight1.getDateFrom());
        assertNotNull(flight1.getDepartFrom());
        assertNotNull(flight1.getDepartTo());
        assertNotNull(flight1.getCode());
        assertNotNull(flight1.getCompany());
    }

    @Test
    @DisplayName("Flight Fields Test When Everything is Not Set")
    void flightFieldsTest2() {
        // 设置测试环境
        Flight flight2 = new Flight();
        // 验证字段
        assertAll("Flight fields validation",
                () -> assertNotEquals(0, flight2.getFlightID(), "Flight ID should not be 0"),
                () -> assertNotNull(flight2.getAirplane(), "Airplane should not be null"),
                () -> assertNotNull(flight2.getDateTo(), "DateTo should not be null"),
                () -> assertNotNull(flight2.getDateFrom(), "DateFrom should not be null"),
                () -> assertNotNull(flight2.getDepartFrom(), "DepartFrom should not be null"),
                () -> assertNotNull(flight2.getDepartTo(), "DepartTo should not be null"),
                () -> assertNotNull(flight2.getCode(), "Code should not be null"),
                () -> assertNotNull(flight2.getCompany(), "Company should not be null")
        );
    }

    @ParameterizedTest
    @DisplayName("Test Whether the Date and Time Format is Correct")
    @ValueSource(strings = {
            "13:30:45 01/10/23",
            "15:30:45 30/09/23"
    })
    void validDateTimeFormatTest(String dateTime) {
        // 验证日期格式
        assertTrue(dateTime.matches("\\d{2}:\\d{2}:\\d{2} \\d{2}/\\d{2}/\\d{2}"));
        // 解析并验证具体的时间和日期值
        assertTrue(validateDateTime(dateTime));
    }

    @ParameterizedTest
    @DisplayName("Test Whether the Date and Time Format is Incorrect")
    @CsvSource({
            "13:30:45 01/10/23, true",
            "15:30:45 32/13/23, false",
            "26:01:77 30/09/23, false"
    })

    private boolean validateDateTime(String dateTime) {
        try {
            // 分割时间和日期部分
            String[] parts = dateTime.split(" ");
            String timePart = parts[0];
            String datePart = parts[1];

            // 分割并验证时间部分
            String[] timeComponents = timePart.split(":");
            int hours = Integer.parseInt(timeComponents[0]);
            int minutes = Integer.parseInt(timeComponents[1]);
            int seconds = Integer.parseInt(timeComponents[2]);

            if (hours < 0 || hours >= 24) {
                System.out.println("Hours should be between 0 and 23");
                return false;
            }
            if (minutes < 0 || minutes >= 60) {
                System.out.println("Minutes should be between 0 and 59");
                return false;
            }
            if (seconds < 0 || seconds >= 60) {
                System.out.println("Seconds should be between 0 and 59");
                return false;
            }

            // 分割并验证日期部分
            String[] dateComponents = datePart.split("/");
            int day = Integer.parseInt(dateComponents[0]);
            int month = Integer.parseInt(dateComponents[1]);
            int year = Integer.parseInt(dateComponents[2]);

            if (month < 1 || month > 12) {
                System.out.println("Month should be between 1 and 12");
                return false;
            }
            if (day < 1 || day > 31) {
                System.out.println("Day should be between 1 and 31");
                return false;
            }

            // 检查月份中的有效天数
            if (month == 4 || month == 6 || month == 9 || month == 11) {
                if (day > 30) {
                    System.out.println("Day should be between 1 and 30 for the given month");
                    return false;
                }
            } else if (month == 2) {
                // 简单处理闰年，假设年份都是2000年后的四位数年份
                boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                if (day > (isLeapYear ? 29 : 28)) {
                    System.out.println("Day should be between 1 and 29 for February in a leap year");
                    return false;
                }
            }

            return true;
        } catch (Exception e) {
            System.out.println("Invalid date or time format");
            return false;
        }
    }

    @Test
    @DisplayName("Test Whether FlightID is Unique")
    public void FlightIDDuplicateTest() throws IllegalAccessException, NoSuchFieldException {

        ArrayList<Object> sampleFlights = new ArrayList<>();
        sampleFlights.add(new Flight(1, "New York", "Los Angeles", "NYLA123", "Delta Airlines", Timestamp.valueOf("2023-04-01 08:00:00"), Timestamp.valueOf("2023-04-01 11:00:00"), new Airplane(1, "Boeing 737", 160, 5000, 20)));
        sampleFlights.add(new Flight(2, "San Francisco", "Chicago", "SFCH456", "United Airlines", Timestamp.valueOf("2023-04-02 09:00:00"), Timestamp.valueOf("2023-04-02 12:00:00"), new Airplane(2, "Airbus A320", 150, 4500, 25)));
        sampleFlights.add(new Flight(3, "Boston", "Miami", "BOMI789", "American Airlines", Timestamp.valueOf("2023-04-03 07:00:00"), Timestamp.valueOf("2023-04-03 10:00:00"), new Airplane(3, "Boeing 757", 180, 5500, 22)));

        // 通过反射设置FlightCollection的flights字段
        Field flightsField = FlightCollection.class.getDeclaredField("flights");
        flightsField.setAccessible(true);
        flightsField.set(null, new ArrayList<>(sampleFlights));

        // 创建一个新的Flight，其FlightID与flightCollection中某个Flight的FlightID相同
        Flight flight1 = new Flight();
        flight1.setFlightID(1); // 故意设置一个重复的FlightID

        // 检查FlightID是否重复
        boolean isDuplicate = FlightCollection.getFlightInfo(flight1.getFlightID()) != null;

        // 断言：期望isDuplicate为true，因为FlightID 1已经存在
        assertFalse(isDuplicate, "FlightID already exists in the collection");
    }

    @Test
    @DisplayName("Test Whether toString Method Returns Correct String Representation of Flight Object")
    public void toStringTest() {
        // 设置测试环境
        Airplane airplane1 = new Airplane(1, "Boeing 747", 160, 5000, 20);
        Flight flight1 = new Flight();
        flight1.setFlightID(1);
        flight1.setAirplane(airplane1);
        flight1.setDateTo(new Timestamp(System.currentTimeMillis()));
        flight1.setDateFrom(new Timestamp(System.currentTimeMillis()));
        flight1.setDepartFrom("New York");
        flight1.setDepartTo("London");
        flight1.setCode("NY123");
        flight1.setCompany("AirTest");

        // 验证toString方法
        assertEquals(
                String.format(
                        "Flight{Airplane{model=%s, business sits=%d, economy sits=%d, crew sits=%d}, date to=%s, date from=%s, depart from='New York', depart to='London', code='NY123', company='AirTest'",
                        airplane1.getAirplaneModel(),
                        airplane1.getBusinessSitsNumber(),
                        airplane1.getEconomySitsNumber(),
                        airplane1.getCrewSitsNumber(),
                        flight1.getDateTo(),
                        flight1.getDateFrom()
                ),
                flight1.toString(),
                "There is a mismatch in the expected and actual string representation of the Flight object."
        );
    }
}

